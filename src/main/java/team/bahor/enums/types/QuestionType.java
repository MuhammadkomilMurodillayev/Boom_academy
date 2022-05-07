package team.bahor.enums.types;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team.bahor.exeptions.exam.NotFoundQuestionTypeException;

@Getter
@RequiredArgsConstructor
public enum QuestionType {
    TEST, // -> OCHIQ TEST
    INPUT; // -> YOPIQ TEST
    public static QuestionType get(String name){
        for (QuestionType value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new NotFoundQuestionTypeException("not found question type");
    }

}
