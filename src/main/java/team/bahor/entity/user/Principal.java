package team.bahor.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import team.bahor.entity.base.BaseGenericEntity;

@Getter
@Setter
@AllArgsConstructor
public class Principal implements BaseGenericEntity {
    private String id;
    private String username;
    private int status;
    private boolean deleted;
}
