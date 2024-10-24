package kopo.userservice.repository.repository;


import kopo.userservice.repository.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 회원 정보 조회
    UserEntity findByUserId(String userId);

    // 아이디 찾기
    UserEntity findByUserNameAndUserEmail(String userName, String userEmail);

    // 비밀번호 찾기
    UserEntity findByUserIdAndUserEmail(String userId, String userEmail);

    // 아이디 중복 체크
    boolean existsByUserId(String userId);

    // 이메일 중복 체크
    boolean existsByUserEmail(String userEmail);

    // 회원 탈퇴
    void deleteByUserId(String userId);
}
