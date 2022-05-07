package team.bahor.controller.exam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionUpdateDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.exam.answerToExamQuestion.AnswerToExamQuestionServiceImpl;

import java.util.List;

@RestController
public class ExamQuestionAnswerController extends AbstractController<AnswerToExamQuestionServiceImpl> {
    public ExamQuestionAnswerController(AnswerToExamQuestionServiceImpl service) {
        super(service);
    }

    public ResponseEntity<String> create(@RequestBody AnswerToExamQuestionCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @RequestMapping(value = PATH + "/examQuestionAnswer/delete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> deleted(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "/examQuestionAnswer/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<AnswerToExamQuestionDto>> get(@PathVariable String id) {
        return new ResponseEntity<>(new DataDto<>(service.get(id)), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/examQuestionAnswer/getAll", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<AnswerToExamQuestionDto>>> getAll() {
        return new ResponseEntity<>(new DataDto<>(service.getAll()), HttpStatus.OK);
    }


    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/examQuestionAnswer/getAll/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<AnswerToExamQuestionDto>>> getAll(@PathVariable String id) {
        return new ResponseEntity<>(new DataDto<>(service.getAll(id)), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/examQuestionAnswer/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody AnswerToExamQuestionUpdateDto updateDto) {
        service.update(updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
