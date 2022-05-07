package team.bahor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.exam.exam.ExamCreateDtoBegin;

@SpringBootTest
class BoomAcademyApplicationTests {

    @SneakyThrows
    @Test
    void contextLoads() {
        ExamCreateDtoBegin dtoBegin = new ExamCreateDtoBegin();
        dtoBegin.setDescription("exam create qildim");
        dtoBegin.setTitle("matematika");
        dtoBegin.setCourseId("dfdfdfdfdfd");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        String s = objectMapper.writeValueAsString(dtoBegin);
        System.out.println("s = " + s);
    }

}
