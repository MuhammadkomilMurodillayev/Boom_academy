package team.bahor.controller.lesson;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.lesson.question.LessonQuestionCreateDto;
import team.bahor.dto.lesson.question.LessonQuestionDto;
import team.bahor.dto.lesson.question.LessonQuestionUpdateDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.lesson.question.LessonQuestionServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/lesson-question")
public class LessonQuestionController extends AbstractController<LessonQuestionServiceImpl> {

    public LessonQuestionController(LessonQuestionServiceImpl service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<DataDto<String>> create(@RequestBody LessonQuestionCreateDto dto) {
        String message = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<LessonQuestionDto>> get(@PathVariable String id) {
        LessonQuestionDto savedCourseDto = service.get(id);
        return new ResponseEntity<>(new DataDto<>(savedCourseDto), HttpStatus.OK);
    }

    @GetMapping("/get-all/{lessonId}")
    public ResponseEntity<DataDto<List<LessonQuestionDto>>> getAll(@PathVariable String lessonId) {
        List<LessonQuestionDto> lessonDtoList = service.getAll(lessonId);
        return new ResponseEntity<>(new DataDto<>(lessonDtoList), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody LessonQuestionUpdateDto dto) {
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
