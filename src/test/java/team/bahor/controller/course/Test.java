package team.bahor.controller.course;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import lombok.SneakyThrows;
import team.bahor.dto.course.saved.SavedCourseDto;

import java.lang.reflect.Type;
import java.util.List;

public class Test {
    @SneakyThrows
    @org.junit.jupiter.api.Test
    public void test(){
        String res="[{\"id\":\"1eef3401-ca8b-48eb-93b0-970a70b424dc\",\"course_id\":\"4199e0fbea33403c97eeaf348ec10a55\",\"user_id\":\"b204fe10-5dd2-49f4-823e-644ab76c8d71\",\"name\":\"english\",\"purchase_count\":null,\"username\":\"ad\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<SavedCourseDto> courseDtoList = objectMapper.readValue(res, new TypeReference<>(){});
        for (SavedCourseDto savedCourseDto : courseDtoList) {
            System.out.println("savedCourseDto = " + savedCourseDto);
        }
        Type type = new TypeToken<List<SavedCourseDto>>() {
        }.getType();


    }
}
