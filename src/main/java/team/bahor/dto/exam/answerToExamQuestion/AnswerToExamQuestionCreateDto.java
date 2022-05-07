package team.bahor.dto.exam.answerToExamQuestion;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import team.bahor.dto.BaseGenericDto;
import team.bahor.enums.types.QuestionType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Setter
@Getter
public class AnswerToExamQuestionCreateDto implements BaseGenericDto {
    private String examQuestionId;

    private String title;

    private boolean correct;
}
