package team.bahor.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.user.AuthUser;
import team.bahor.repositories.base.BaseGenericRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, String>, BaseGenericRepository {

    Optional<AuthUser> findByUsernameAndDeletedFalse(String username);

    @Transactional
    @Modifying
    @Query(value = "update boom_academy.main.auth_users set is_deleted = 1, updated_at = now() where id = ?1 and is_deleted = 0", nativeQuery = true)
    void deleted(String id);

    @Transactional
    @Modifying
    @Query(value = "update boom_academy.main.auth_users set status = 100, updated_at = now() where id = ?1 and is_deleted = 0", nativeQuery = true)
    void blocked(String id);

    @Transactional
    @Modifying
    @Query(value = "update boom_academy.main.auth_users set status = 0, updated_at = now() where id = ?1 and is_deleted = 0", nativeQuery = true)
    void changeStatusActive(String userId);

    @Query(value = "select * from boom_academy.main.auth_users au where au.email = ?1 or au.username = ?2 and is_deleted = 0", nativeQuery = true)
    AuthUser findByEmailOrUsername(String email, String username);

    @Query(value = "select * from boom_academy.main.auth_users where id = ?1 and status = 0 and is_deleted = 0", nativeQuery = true)
    AuthUser findByIdAuthorizated(String sessionId);

    @Query(value = "select * from boom_academy.main.auth_users where id = ?2 and balance >= ?1 and status = 0 and is_deleted = 0", nativeQuery = true)
    Double validUserBalance(Double nativePrice, String sessionId);

    @Modifying
    @Query(value = "update boom_academy.main.auth_users set balance = ?1, updated_at = now() where id = ?2 and status = 0 and is_deleted = 0", nativeQuery = true)
    void changeUserBalance(double newBalance, String sessionId);
}
