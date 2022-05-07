package team.bahor.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import team.bahor.entity.user.UserData;

public interface UserDataRepository extends JpaRepository<UserData, String> {
}
