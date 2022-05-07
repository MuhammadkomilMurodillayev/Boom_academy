package team.bahor.entity.user;

import lombok.*;
import org.hibernate.annotations.Where;
import team.bahor.entity.base.Auditable;
import team.bahor.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "auth_users", indexes = {
        @Index(name = "auth_user_status_index", columnList = "status"),
        @Index(name = "auth_user_email_index", columnList = "email"),
        @Index(name = "auth_user_phone_index", columnList = "phone")
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = 0")
public class AuthUser extends Auditable {

    private String fullName;

    @Column(nullable = false,unique = true)
    private String username;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private Double balance;

    private String photo_url;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Builder(builderMethodName = "childBuilder")

    public AuthUser(String id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, short status, String fullName, String username, String password, Role role, Double balance, String photo_url, String email, String phone) {
        super(id, createdAt, updatedAt, deleted, status);
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
        this.photo_url = photo_url;
        this.email = email;
        this.phone = phone;
    }
}
