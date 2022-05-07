package team.bahor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.bahor.config.encryption.PasswordEncoderConfigurer;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;
import team.bahor.dto.exam.exam.*;
import team.bahor.dto.exam.examQuestion.ExamQuestionCreateDto;
import team.bahor.dto.search.ResultOfSearch;
import team.bahor.entity.exam.ExamQuestion;
import team.bahor.repositories.exam.ExamQuestionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//@SpringBootTest
public class ExamQuestionTest {

    @Autowired
    private ExamQuestionRepository repository;

    @Test
    @SneakyThrows
    public void test1() {
        ExamCreateDtoBegin dtoBegin = new ExamCreateDtoBegin();
        dtoBegin.setDescription("exam create qildim");
        dtoBegin.setTitle("matematika");
        dtoBegin.setCourseId("3333333333333333333333333333");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("objectMapper.writeValueAsString(dtoBegin) = " + objectMapper.writeValueAsString(dtoBegin));
    }

    @Test
    @SneakyThrows
    public void test2() {
        ExamCreateDtoEnd examCreateDtoEnd = new ExamCreateDtoEnd();
        examCreateDtoEnd.setId("a9e3403d-d3c9-4202-9b68-1cfbc6a0644c");
        examCreateDtoEnd.setDuration(120000L);
        HashMap<String, Integer> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(Integer.toString(2), 3);
        objectObjectHashMap.put(Integer.toString(3), 3);
        examCreateDtoEnd.setQuestionCounts(objectObjectHashMap);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("objectMapper.writeValueAsString(examCreateDtoEnd) = " + objectMapper.writeValueAsString(examCreateDtoEnd));
    }

    @Test
    public void test3() {
        PasswordEncoderConfigurer passwordEncoderConfigurer = new PasswordEncoderConfigurer();
        System.out.println("passwordEncoderConfigurer.passwordEncoder().encode(\"123\") = " + passwordEncoderConfigurer.passwordEncoder().encode("123"));
    }


    @SneakyThrows
    @Test
    public void examQuestionCreateDto() {
        ExamQuestionCreateDto examQuestionCreateDto = new ExamQuestionCreateDto();
        examQuestionCreateDto.setExamId("a9e3403d-d3c9-4202-9b68-1cfbc6a0644c");
        examQuestionCreateDto.setTitle("100*10");
        examQuestionCreateDto.setType("TEST");
        examQuestionCreateDto.setMark(3);
        List<AnswerToExamQuestionCreateDto> answers = new ArrayList<>();

        AnswerToExamQuestionCreateDto answerToExamQuestionCreateDto1 = new AnswerToExamQuestionCreateDto();
        answerToExamQuestionCreateDto1.setTitle("1000");
        answerToExamQuestionCreateDto1.setCorrect(true);
        answers.add(answerToExamQuestionCreateDto1);

        AnswerToExamQuestionCreateDto answerToExamQuestionCreateDto2 = new AnswerToExamQuestionCreateDto();
        answerToExamQuestionCreateDto2.setTitle("13");
        answerToExamQuestionCreateDto2.setCorrect(false);
        answers.add(answerToExamQuestionCreateDto2);

        AnswerToExamQuestionCreateDto answerToExamQuestionCreateDto3 = new AnswerToExamQuestionCreateDto();
        answerToExamQuestionCreateDto3.setTitle("16");
        answerToExamQuestionCreateDto3.setCorrect(false);
        answers.add(answerToExamQuestionCreateDto3);

        AnswerToExamQuestionCreateDto answerToExamQuestionCreateDto4 = new AnswerToExamQuestionCreateDto();
        answerToExamQuestionCreateDto4.setTitle("35");
        answerToExamQuestionCreateDto4.setCorrect(false);
        answers.add(answerToExamQuestionCreateDto4);

        examQuestionCreateDto.setAnswers(answers);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        String s = objectMapper.writeValueAsString(examQuestionCreateDto);
        System.out.println(s);
    }

    @Test
    public void test4() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println("atomicInteger.getAndIncrement() = " + atomicInteger.getAndIncrement());
        System.out.println("atomicInteger.getAndIncrement() = " + atomicInteger.getAndIncrement());
    }

    @Test
    public void test5() {
//        ExamQuestionRepository
    }

    @SneakyThrows
    @Test
    public void test6() {
        ExamSolveDto examSolveDto = new ExamSolveDto();
        examSolveDto.setExamUserId("725b806e-bf74-449b-a1a8-e76e563dfbf1");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("objectMapper.writeValueAsString(objectMapper) = " + objectMapper.writeValueAsString(examSolveDto));
    }

    @SneakyThrows
    @Test
    public void test7() {
        ExamSolveDto examSolveDto = new ExamSolveDto();
        examSolveDto.setExamUserId("d77ab65b-ba30-4364-91bf-ccd41538606c");
        examSolveDto.setOrder(1);
        examSolveDto.setNextOrder(2);
        examSolveDto.setMarkedAnswerId("c69a37b0-a08e-4b8b-abd4-e70a6999c36f");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("objectMapper.writeValueAsString(objectMapper) = " + objectMapper.writeValueAsString(examSolveDto));
    }/*{"examUserId":"b1adca41-1cbb-4b85-ada7-3365097cab72","markedAnswerId":"db679a77-2ced-4bdc-a4a2-50e7826b2475","nextOrder":2}
     */

    @SneakyThrows
    @Test
    public void test8() {
        String dtos = "{\"course_id\":\"1111111111111111111111111111\",\"course_title\":\"Matematika\",\"exam_id\":\"cf874f95-0c48-44d4-b643-68553ada04cb\",\"exam_title\":\"matematika\",\"question_count\":3,\"max_mark\":5,\"percentage\":100,\"exam_question_dtos\":[{\"type\":\"TEST\",\"mark\":2,\"user_mark_answer_title\":\"6\",\"title\":\"3*2\",\"correctly_solved\":1,\"answers\":[{\"exam_question_id\":\"db679a77-2ced-4bdc-a4a2-50e7826b2475\",\"title\":\"6\",\"correct\":1},{\"exam_question_id\":\"db679a77-2ced-4bdc-a4a2-50e7826b2475\",\"title\":\"13\",\"correct\":0},{\"exam_question_id\":\"db679a77-2ced-4bdc-a4a2-50e7826b2475\",\"title\":\"16\",\"correct\":0},{\"exam_question_id\":\"db679a77-2ced-4bdc-a4a2-50e7826b2475\",\"title\":\"35\",\"correct\":0}]},{\"type\":\"TEST\",\"mark\":3,\"user_mark_answer_title\":\"12\",\"title\":\"3*4\",\"correctly_solved\":1,\"answers\":[{\"exam_question_id\":\"482f3593-b2bd-4bd5-beac-3079e7f374c0\",\"title\":\"12\",\"correct\":1},{\"exam_question_id\":\"482f3593-b2bd-4bd5-beac-3079e7f374c0\",\"title\":\"13\",\"correct\":0},{\"exam_question_id\":\"482f3593-b2bd-4bd5-beac-3079e7f374c0\",\"title\":\"16\",\"correct\":0},{\"exam_question_id\":\"482f3593-b2bd-4bd5-beac-3079e7f374c0\",\"title\":\"35\",\"correct\":0}]}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        FinishDto finishDto = objectMapper.readValue(dtos, FinishDto.class);
        System.out.println("finishDto.getCourseId() = " + finishDto.getCourseId());
        System.out.println("finishDto.getCourseTitle() = " + finishDto.getCourseTitle());
        System.out.println("finishDto.getExamId() = " + finishDto.getExamId());

    }

    @Test
    @SneakyThrows
    public void test9() {
        ExamUpdateDto examUpdateDto = new ExamUpdateDto();
        examUpdateDto.setId("cf874f95-0c48-44d4-b643-68553ada04cb");
        examUpdateDto.setDescription("update boldi");
        examUpdateDto.setDuration(500000L);
        System.out.println("new ObjectMapper().writeValueAsString(examUpdateDto) = " + new ObjectMapper().writeValueAsString(examUpdateDto));
    }

    @Test
    @SneakyThrows
    void testGetById() {
        String string = "{\"full_name\":\"Abdullatipov Abdulloh\",\"course_name\":\"fizika\",\"percentage\":33.33333333333333}";
        ObjectMapper objectMapper = new ObjectMapper();
        Certificate certificate = objectMapper.readValue(string, Certificate.class);
        System.out.println("certificate = " + certificate);

    }

    @Test
    @SneakyThrows
    void test10() {
        String string = "[{\"id\":\"1111111111111111111111111\",\"name\":\"matematika\"},{\"id\":\"44444444444444444444444444\",\"name\":\"matematika\"}]";
        List<ResultOfSearch> result = new ObjectMapper().readValue(string, new TypeReference<List<ResultOfSearch>>() {
        });
        result.forEach(resultOfSearch -> {
            System.out.println(resultOfSearch.getId());
        });
    }


}
