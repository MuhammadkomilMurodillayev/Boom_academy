package team.bahor.controller.file;


import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.bahor.controller.AbstractController;
import team.bahor.dto.file.FileStorageCreateDto;
import team.bahor.dto.file.FileStorageDto;
import team.bahor.services.file.FileStorageService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/file/")
public class FileStorageController extends AbstractController<FileStorageService> {

    public FileStorageController(FileStorageService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/uploadFile/{lessonId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void store(@NotNull @PathVariable(name = "lessonId") String lessonId,@NotNull @RequestBody MultipartFile multipartFile) {

        String fileId = service.create(multipartFile, lessonId);

        System.out.print(fileId);

    }

    @PostMapping(value = PATH + "/uploadVideoFile/{lessonId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void storeVideo(@PathVariable(name = "lessonId") String lessonId, @RequestBody MultipartFile multipartFile) {

        String fileId = service.uploadVideoFile(multipartFile, lessonId);

        System.out.print(fileId);

    }

//    @GetMapping(value = PATH + "/{lessonId}/get/{id}")
//    public ResponseEntity<FileStorageDto> getFile(@PathVariable(name = "lessonId") String lessonId, @PathVariable(name = "id") String id) {
//        FileStorageDto dto = service.get(id);
//        return ResponseEntity.ok(dto);
//    }

    @GetMapping(value = PATH + "/getAll/{lessonId}")
    public ResponseEntity<List<FileStorageDto>> getAllFile(@PathVariable String lessonId) {
        return ResponseEntity.ok(service.getAll(lessonId));
    }

//    @GetMapping(value = PATH + "/getAsResource/{generatedName}")
//    public ResponseEntity<Resource> getFileAsResource(@PathVariable(name = "id") String id) {
//
//        FileStorageDto fileStorageDto = service.getAsResource(id);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=\"" + fileStorageDto.getOriginalName() + "\"").body(fileStorageDto.getFile());
//    }

    @DeleteMapping(value = PATH + "/delete/{id}")
    public void deleteAll(@PathVariable(name = "id") String id) {
        service.delete(id);
    }

}
