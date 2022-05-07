package team.bahor.controller.accountant;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.bahor.controller.AbstractController;
import team.bahor.services.accountant.AccountantService;

import javax.xml.transform.Source;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;

@RestController
public class AccountantController extends AbstractController<AccountantService> {

    public AccountantController(AccountantService service) {
        super(service);
    }

    @GetMapping("/accountant")
    public ResponseEntity<Resource> accountant() {
        String filename = "tutorials.xlsx";
        InputStreamResource file = new InputStreamResource(service.accountant());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }


}
