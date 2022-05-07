package team.bahor.controller.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.course.CourseCreateDto;
import team.bahor.dto.course.CourseDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.course.CourseService;

import java.util.List;


@RestController("/course/")
public class CourseController extends AbstractController<CourseService> {

    public CourseController(CourseService service) {
        super(service);
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<DataDto<CourseDto>> get(@PathVariable String id) {
        CourseDto courseDto = service.get(id);
        return new ResponseEntity<>(new DataDto<>(courseDto), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @GetMapping("getCourses")
    public ResponseEntity<DataDto<List<CourseDto>>> getAll() {
        return new ResponseEntity<>(new DataDto<>(service.getAll()), HttpStatus.OK);
    }

    @GetMapping("getActiveCourses")
    public ResponseEntity<DataDto<List<CourseDto>>> getActiveCourses() {
        return new ResponseEntity<>(new DataDto<>(service.getActiveCourses()), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @GetMapping("getNonActiveCourses")
    public ResponseEntity<DataDto<List<CourseDto>>> getNonActiveCourses() {
        return new ResponseEntity<>(new DataDto<>(service.getNonActiveCourses()), HttpStatus.OK);
    }

    @GetMapping("getMyCourses")
    public ResponseEntity<DataDto<List<CourseDto>>> getMyCourses() {
        return new ResponseEntity<>(new DataDto<>(service.getMyCourses()), HttpStatus.OK);
    }

    @GetMapping("getMyActiveCourses")
    public ResponseEntity<DataDto<List<CourseDto>>> getMyActiveCourses() {
        return new ResponseEntity<>(new DataDto<>(service.getMyActiveCourses()), HttpStatus.OK);
    }

    @GetMapping("getMyNonActiveCourses")
    public ResponseEntity<DataDto<List<CourseDto>>> getMyNonActiveCourses() {
        return new ResponseEntity<>(new DataDto<>(service.getMyNonActiveCourses()), HttpStatus.OK);
    }

    @PutMapping("activated/{id}")
    public ResponseEntity<DataDto<String>> activatedCourse(@PathVariable String id) {
        service.activated(id);
        return new ResponseEntity<>(new DataDto<>("activated"), HttpStatus.OK);
    }

    @PutMapping("nonActivated/{id}")
    public ResponseEntity<DataDto<String>> nonActivatedCourse(@PathVariable String id) {
        service.nonActivated(id);
        return new ResponseEntity<>(new DataDto<>("non activated"), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<DataDto<String>> create(@RequestBody CourseCreateDto dto) {
        String id = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<DataDto<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new DataDto<>("deleted"), HttpStatus.OK);
    }

}

