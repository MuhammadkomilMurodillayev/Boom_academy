package team.bahor.controller.lesson;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.lesson.note.NotesToLessonsCreateDto;
import team.bahor.dto.lesson.note.NotesToLessonsDto;
import team.bahor.dto.lesson.note.NotesToLessonsUpdateDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.lesson.notes.NotesToLessonsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/notes-to-lessons")
public class NotesToLessonsController extends AbstractController<NotesToLessonsServiceImpl> {
    public NotesToLessonsController(NotesToLessonsServiceImpl service) {
        super(service);
    }


    @PostMapping("/create")
    public ResponseEntity<DataDto<String>> create(@RequestBody NotesToLessonsCreateDto dto) {
        String message = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<NotesToLessonsDto>> get(@PathVariable String id) {
        NotesToLessonsDto savedCourseDto = service.get(id);
        return new ResponseEntity<>(new DataDto<>(savedCourseDto), HttpStatus.OK);
    }

    @GetMapping("/get-all/{userId}")
    public ResponseEntity<DataDto<List<NotesToLessonsDto>>> getAll(@PathVariable String userId) {
        List<NotesToLessonsDto> lessonDtoList = service.getAll(userId);
        return new ResponseEntity<>(new DataDto<>(lessonDtoList), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody NotesToLessonsUpdateDto dto) {
        dto.setId(id);
        service.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<DataDto<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
