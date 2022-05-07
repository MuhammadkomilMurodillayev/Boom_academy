package team.bahor.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import team.bahor.entity.base.BaseGenericEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "user_activation_code")
@NoArgsConstructor
@AllArgsConstructor
public class UserActivationCode implements BaseGenericEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "active_time", columnDefinition = "timestamp default now() + interval '2 hours'")
    private LocalDateTime activeTime;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "activation_code", nullable = false)
    private String activationCode;

    @Column(nullable = false)
    private String email;

    @Column(name = "used_code", columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean usedCode;

    public UserActivationCode(String userId, String activationCode, String email, LocalDateTime activeTime) {
        this.userId = userId;
        this.activationCode = activationCode;
        this.email = email;
        this.activeTime = activeTime;
    }

}
