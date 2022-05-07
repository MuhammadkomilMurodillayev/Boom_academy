package team.bahor.controller;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.bahor.dto.exam.exam.ExamCreateDtoEnd;
import team.bahor.dto.responce.DataDto;
import team.bahor.exeptions.fileStore.FileStorageException;
import team.bahor.utils.Utils;

import java.util.HashMap;

@RestController
public class TestController {
    @GetMapping("/test/exception")
    public ResponseEntity<DataDto> testExceptionHandler() {
        throw new FileStorageException("MuhammadKomil ishladi");
    }

    @GetMapping("/test/token")
    public ResponseEntity<DataDto<String>> testToken() {
        System.out.println("Utils.getSessionId() = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new ResponseEntity<>(new DataDto<>(Utils.getSessionId()), HttpStatus.OK);
    }


    @GetMapping("test/auth")
    public ResponseEntity<ExamCreateDtoEnd> test(){
        ExamCreateDtoEnd examCreateDtoEnd=new ExamCreateDtoEnd();
        examCreateDtoEnd.setId("7ea78b2a-0413-4001-994c-45eb2bc16e5e");
        examCreateDtoEnd.setDuration(120000L);
        HashMap<String,Integer> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(Integer.toString(2),5);
        objectObjectHashMap.put(Integer.toString(3),5);
        examCreateDtoEnd.setQuestionCounts(objectObjectHashMap);
        return new ResponseEntity<>(examCreateDtoEnd,HttpStatus.OK);
    }


//    @GetMapping("/test/auth")

}
