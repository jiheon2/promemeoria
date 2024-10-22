package kopo.videochatservice;

import kopo.videochatservice.dto.NotificationDTO;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class VideoChatServiceApplication implements CommandLineRunner {

    private static String TOPIC_NAME = "test";

    @Autowired
    private KafkaTemplate<String, NotificationDTO> notificationDTOKafkaTemplate;
//
//    public static void main(String[] args) {
//        SpringApplication.run(VideoChatServiceApplication.class, args);
//    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(VideoChatServiceApplication.class);
        application.run(args);
    }

    @Override
    public void run(String ...args) {

        NotificationDTO dto = NotificationDTO.builder().message("hi").build();

        CompletableFuture<SendResult<String, NotificationDTO>> future = notificationDTOKafkaTemplate.send(TOPIC_NAME, dto);
        future.thenAccept(result -> {
            RecordMetadata metadata = result.getRecordMetadata();
            System.out.println("Message sent successfully to topic: " + metadata.topic() + " partition:" + metadata.partition() + " offset:" + metadata.offset());
        }).exceptionally(ex -> {
            System.err.println("Message failed to send: " + ex.getMessage());
            return null;
        });
    }
}
