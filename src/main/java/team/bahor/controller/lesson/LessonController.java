package team.bahor.controller.lesson;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.bahor.controller.AbstractController;
import team.bahor.dto.lesson.LessonCreateDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.lesson.LessonServiceImpl;

@RestController
public class LessonController extends AbstractController<LessonServiceImpl> {
    public LessonController(LessonServiceImpl service) {
        super(service);
    }

    @GetMapping(value = PATH + "/lesson/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<DataDto<String>> create(@RequestBody LessonCreateDto dto, @RequestBody MultipartFile file) {
        return new ResponseEntity<>(new DataDto<>(service.create(dto, file)), HttpStatus.OK);
    }


}
