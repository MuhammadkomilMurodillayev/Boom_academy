package team.bahor.controller.finance;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bahor.controller.AbstractController;
import team.bahor.dto.finance.CouponCreateDto;
import team.bahor.dto.finance.CouponDto;
import team.bahor.dto.finance.CouponUpdateDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.services.finance.CouponServiceImp;

import javax.validation.Valid;

@RestController
@RequestMapping("/coupon/")
public class CouponController extends AbstractController<CouponServiceImp> {


    public CouponController(CouponServiceImp service) {
        super(service);
    }

    @PostMapping("create")
    public ResponseEntity<DataDto<String>> create(@Valid  @RequestBody CouponCreateDto dto) {
        return new ResponseEntity<>(new DataDto<>(service.create(dto)), HttpStatus.OK);

    }

    @GetMapping("get/{id}")
    public ResponseEntity<DataDto<CouponDto>> get(@Valid @PathVariable String id) {
        return new ResponseEntity<>(new DataDto<>(service.get(id)), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<DataDto<String>> update(@Valid @PathVariable String id, @RequestBody CouponUpdateDto dto) {
        dto.setId(id);
        service.update(dto);
        return new ResponseEntity<>(new DataDto<>("Updated"), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<DataDto<String>> delete(@Valid  @PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new DataDto<>("deleted"), HttpStatus.OK);
    }


}
