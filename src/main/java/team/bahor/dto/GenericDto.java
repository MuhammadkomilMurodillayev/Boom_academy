package team.bahor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public abstract class   GenericDto implements BaseGenericDto {
    private String id;
}
