package team.bahor.controller.tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.course.CourseDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.dto.tag.TagCreateDto;
import team.bahor.dto.tag.TagDto;
import team.bahor.services.tag.TagsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagsController extends AbstractController<TagsServiceImpl> {

    public TagsController(TagsServiceImpl service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<DataDto<String>> create(@RequestBody TagCreateDto dto) {
        String message = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

    @GetMapping("/search-courses/{name}")
    public ResponseEntity<DataDto<List<CourseDto>>> create(@PathVariable String name) {
        List<CourseDto> courseDtoList = service.searchCourse(name);
        return new ResponseEntity<>(new DataDto<>(courseDtoList), HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<TagDto>> get(@PathVariable String id) {
        TagDto savedCourseDto = service.get(id);
        return new ResponseEntity<>(new DataDto<>(savedCourseDto), HttpStatus.OK);
    }

    @GetMapping("/get-all/{courseId}")
    public ResponseEntity<DataDto<List<TagDto>>> getAll(@PathVariable String courseId) {
        List<TagDto> courseDtoList = service.getAll(courseId);
        return new ResponseEntity<>(new DataDto<>(courseDtoList), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<DataDto<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
