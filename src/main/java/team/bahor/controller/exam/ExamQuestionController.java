package team.bahor.controller.exam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.exam.examQuestion.ExamQuestionCreateDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionUpdateDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.exam.examQuestion.ExamQuestionServiceImpl;

import java.util.List;

@RestController
public class ExamQuestionController extends AbstractController<ExamQuestionServiceImpl> {
    public ExamQuestionController(ExamQuestionServiceImpl service) {
        super(service);
    }


    @RequestMapping(value = PATH + "/examQuestion/create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody ExamQuestionCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/examQuestion/delete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> deleted(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //todo shuni oqtuvchi ham koradigan qilib anation yarat
    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/examQuestion/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<ExamQuestionDto>> get(@PathVariable String id) {
        return new ResponseEntity<>(new DataDto<>(service.get(id)), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/examQuestion/block/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> block(@PathVariable String id) {
        service.block(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/examQuestion/getAll", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<ExamQuestionDto>>> getAll() {
        return new ResponseEntity<>(new DataDto<>(service.getAll()), HttpStatus.OK);
    }


    //todo shuni oqtuvchi ham koradigan qilib anation yarat
    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/examQuestion/getAll/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<ExamQuestionDto>>> getAll(@PathVariable String id) {
        return new ResponseEntity<>(new DataDto<>(service.getAll(id)), HttpStatus.OK);
    }

    @RequestMapping(value = PATH+"/examQuestion/update",method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody ExamQuestionUpdateDto updateDto) {
        service.update(updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
