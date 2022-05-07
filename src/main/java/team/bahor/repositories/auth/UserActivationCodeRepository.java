package team.bahor.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.user.UserActivationCode;

import javax.transaction.Transactional;
import java.util.UUID;

public interface UserActivationCodeRepository extends JpaRepository<UserActivationCode, String> {

    @Query(value = "select * from boom_academy.main.user_activation_code au  where au.activation_code = ?1 and au.email = ?2 and au.used_code = 0 and  au.active_time > current_timestamp", nativeQuery = true)
    UserActivationCode checkingCode(String activationCode, String email);


    @Transactional
    @Modifying
    @Query(value = "update boom_academy.main.user_activation_code set used_code = 1 where id = ?1", nativeQuery = true)
    void updateUsedCode(UUID id);

}
