package team.bahor.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.bahor.entity.base.BaseGenericEntity;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Table(name = "user_data", indexes = {
        @Index(name = "user_date_user_id_index", columnList = "userId")
})
@Entity
@NoArgsConstructor
public class UserData implements BaseGenericEntity {
    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, columnDefinition = "float4 default 0")
    private float rating; // -> this is overall rating of a teacher
}
