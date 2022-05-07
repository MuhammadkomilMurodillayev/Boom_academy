package team.bahor.services.finance;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.finance.CouponCreateDto;
import team.bahor.dto.finance.CouponDto;
import team.bahor.dto.finance.CouponUpdateDto;
import team.bahor.mappers.finance.CouponMapper;
import team.bahor.repositories.finance.CouponRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.services.base.GenericCrudService;
import team.bahor.validators.finance.CouponValidator;

import java.util.List;

@Service
public interface CouponService  extends GenericCrudService<
        CouponDto,
        CouponCreateDto,
        CouponUpdateDto,
        String> {
}
