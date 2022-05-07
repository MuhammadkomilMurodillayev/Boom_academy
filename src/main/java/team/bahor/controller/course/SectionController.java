package team.bahor.controller.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.responce.DataDto;
import team.bahor.dto.course.section.SectionCreateDto;
import team.bahor.dto.course.section.SectionDto;
import team.bahor.dto.course.section.SectionPositionUpdateDto;
import team.bahor.dto.course.section.SectionUpdateDto;
import team.bahor.services.course.section.SectionServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController extends AbstractController<SectionServiceImpl> {

    public SectionController(SectionServiceImpl service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<DataDto<String>> create(@RequestBody SectionCreateDto dto) {
        String message = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SectionDto> get(@PathVariable String id) {
        SectionDto sectionDto = service.get(id);
        return new ResponseEntity<>(sectionDto, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<SectionDto>> getAll() {
        List<SectionDto> sectionDtoList = service.getAll();
        return new ResponseEntity<>(sectionDtoList, HttpStatus.OK);
    }


    @GetMapping("/get-course-sections/{id}")
    public ResponseEntity<List<SectionDto>> getCourseSections(@PathVariable String id) {
        List<SectionDto> sectionAllDto = service.getCourseSections(id);
        return new ResponseEntity<>(sectionAllDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody SectionUpdateDto dto) {
        dto.setId(id);
        service.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update-position/{id}")
    public ResponseEntity<Void> updatePosition(@PathVariable String id, @RequestBody SectionPositionUpdateDto dto) {
        dto.setId(id);
        service.updatePosition(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
