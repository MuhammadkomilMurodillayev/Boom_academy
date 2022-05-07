package team.bahor.controller.lesson;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionCreateDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionUpdateDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.lesson.answer.AnswerLessonQuestionServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/answer-lesson-question")
public class AnswerLessonQuestionController extends AbstractController<AnswerLessonQuestionServiceImpl> {
    public AnswerLessonQuestionController(AnswerLessonQuestionServiceImpl service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<DataDto<String>> create(@RequestBody AnswerLessonQuestionCreateDto dto) {
        String message = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<AnswerLessonQuestionDto>> get(@PathVariable String id) {
        AnswerLessonQuestionDto savedCourseDto = service.get(id);
        return new ResponseEntity<>(new DataDto<>(savedCourseDto), HttpStatus.OK);
    }

    @GetMapping("/get-all/{questionId}")
    public ResponseEntity<DataDto<List<AnswerLessonQuestionDto>>> getAll(@PathVariable String questionId) {
        List<AnswerLessonQuestionDto> lessonDtoList = service.getAll(questionId);
        return new ResponseEntity<>(new DataDto<>(lessonDtoList), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody AnswerLessonQuestionUpdateDto dto) {
        dto.setId(id);
        service.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateLikeCount(@PathVariable String id) {
        service.updateLikeCount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<DataDto<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
