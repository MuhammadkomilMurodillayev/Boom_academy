package team.bahor.controller.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.course.saved.SavedCourseCreateDto;
import team.bahor.dto.course.saved.SavedCourseDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.course.saved.SavedCourseServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/saved-course")
public class SavedCourseController extends AbstractController<SavedCourseServiceImpl> {

    public SavedCourseController(SavedCourseServiceImpl service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<DataDto<String>> create(@RequestBody SavedCourseCreateDto dto) {
        String message = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<SavedCourseDto>> get(@PathVariable String id) {
        SavedCourseDto savedCourseDto = service.get(id);
        return new ResponseEntity<>(new DataDto<>(savedCourseDto), HttpStatus.OK);
    }

    @GetMapping("/get-all/{userId}")
    public ResponseEntity<DataDto<List<SavedCourseDto>>> getAll(@PathVariable String userId) {
        List<SavedCourseDto> courseDtoList = service.getAllUserSavedCourse(userId);
        return new ResponseEntity<>(new DataDto<>(courseDtoList), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<DataDto<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
