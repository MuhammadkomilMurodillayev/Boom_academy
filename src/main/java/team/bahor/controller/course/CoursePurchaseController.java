package team.bahor.controller.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.course.purchase.CoursePurchaseCreateDto;
import team.bahor.dto.course.purchase.CoursePurchaseDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.course.purchase.CoursePurchaseServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course-purchase")
public class  CoursePurchaseController extends AbstractController<CoursePurchaseServiceImpl> {
    public CoursePurchaseController(CoursePurchaseServiceImpl service) {
        super(service);
    }


    @PostMapping("/buy")
    public ResponseEntity<DataDto<String>> create(@Valid @RequestBody CoursePurchaseCreateDto dto) {
        String message = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

//    @GetMapping("/get/{id}")
//    public ResponseEntity<DataDto<CoursePurchaseDto>> get(@PathVariable String id) {
//        CoursePurchaseDto savedCourseDto = service.get(id);
//        return new ResponseEntity<>(new DataDto<>(savedCourseDto), HttpStatus.OK);
//    }

    @GetMapping("/get-all/{userId}")
    public ResponseEntity<DataDto<List<CoursePurchaseDto>>> getAll(@PathVariable String userId) {
        List<CoursePurchaseDto> courseDtoList = service.getAll(userId);
        return new ResponseEntity<>(new DataDto<>(courseDtoList), HttpStatus.OK);
    }


    /*
     * > Kurslarni  qay taribda sotib olish trelloda
     * > Endi sotib olinga kurslar haqida :
     *  - Agar user shaxsiy kabinentidan mening kurslarimni tanlasa u sotib olgan hamma kurslar olib kelinadi
     *  - Agar Course avtori kursni haqida malumot kormoqchin bolsa yani course ni sotib olgan odamlarni kora olish
     *    imkoni boladi
     *  - Agar user bitta kursining ustidan bossa osha kurs dalnilari olib kelinadi yani lessonlar va boshqalar
     *  - (Delete va Update haqida keyn gaplashiladi)
     */
}
