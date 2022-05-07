package team.bahor.controller.exam;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.exam.exam.*;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.exam.exam.ExamServiceImpl;
import team.bahor.services.exam.exam.ExportPdfService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class ExamController extends AbstractController<ExamServiceImpl> {
    public ExamController(ExamServiceImpl service, ExportPdfService exportPdfService) {
        super(service);
        this.exportPdfService = exportPdfService;
    }

    private final ExportPdfService exportPdfService;


    @RequestMapping(value = {PATH + "/exam/createBegin"}, method = RequestMethod.POST)
    public ResponseEntity<DataDto<String>> create(@RequestBody ExamCreateDtoBegin dtoBegin) {
        return new ResponseEntity<>(new DataDto<>(service.create(dtoBegin)), HttpStatus.CREATED);
    }

    @RequestMapping(value = {PATH + "/exam/createEnd"}, method = RequestMethod.POST)
    public ResponseEntity<DataDto<String>> create(@RequestBody ExamCreateDtoEnd dto) {
        System.out.println(dto);
        return new ResponseEntity<>(new DataDto<>(service.create(dto)), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/exam/delete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> deleted(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/exam/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<ExamDto>> get(@PathVariable String id) {
        return new ResponseEntity<>(new DataDto<>(service.get(id)), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/exam/block/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> block(@PathVariable String id) {
        service.block(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/exam/getAll", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<ExamDto>>> getAll() {
        return new ResponseEntity<>(new DataDto<>(service.getAll()), HttpStatus.OK);
    }


    @RequestMapping(value = PATH + "/exam/createExamUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<String>> createExamUser(@PathVariable String id) {
        /*
         * bu yerda id examId
         * */
        return new ResponseEntity<>(new DataDto<>(service.createExamUser(id)), HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "/exam/informationForCreateExamUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<InformationForCreateExamUser>> informationForCreateExamUser(@PathVariable String id) {
        /*
         * bu yerda id examId
         * */
        return new ResponseEntity<>(new DataDto<>(service.informationForCreateExamUser(id)), HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "exam/solve", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<ExamSolveDto>> solve(@RequestBody ExamSolveDto examSolveDto) {
        return new ResponseEntity<>(new DataDto<>(service.solve(examSolveDto)), HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "exam/finish/{examUserId}", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<FinishDto>> finish(@PathVariable String examUserId) {
        return new ResponseEntity<>(new DataDto<>(service.finish(examUserId)), HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "exam/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody ExamUpdateDto dto) {
        service.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/certificate/{id}", method = RequestMethod.GET)
    public void downloadReceipt(HttpServletResponse response, @PathVariable String id) throws IOException {
        Map<String, Object> data = service.createData(id);
        ByteArrayInputStream byteArrayInputStream = exportPdfService.exportPdfCertificate("certificate1", data);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
        //todo exception togirla
    }


}
