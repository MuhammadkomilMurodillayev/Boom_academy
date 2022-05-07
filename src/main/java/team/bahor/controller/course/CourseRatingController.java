package team.bahor.controller.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.course.rating.CourseRatingCreateDto;
import team.bahor.dto.course.rating.CourseRatingDto;
import team.bahor.dto.course.rating.CourseRatingUpdateDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.course.rating.CourseRatingServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/course-rating")
public class CourseRatingController extends AbstractController<CourseRatingServiceImpl> {
    public CourseRatingController(CourseRatingServiceImpl service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<DataDto<String>> create(@RequestBody CourseRatingCreateDto dto) {
        String message = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<CourseRatingDto>> get(@PathVariable String id) {
        CourseRatingDto courseRatingDto = service.get(id);
        return new ResponseEntity<>(new DataDto<>(courseRatingDto), HttpStatus.OK);
    }

    @GetMapping("/get-course-ratings/{id}")
    public ResponseEntity<DataDto<List<CourseRatingDto>>> getAll(@PathVariable String id) {
        List<CourseRatingDto> courseRatingDtos = service.getAll(id);
        return new ResponseEntity<>(new DataDto<>(courseRatingDtos), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DataDto<Void>> update(@PathVariable String id, @RequestBody CourseRatingUpdateDto dto) {
        dto.setId(id);
        service.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataDto<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
