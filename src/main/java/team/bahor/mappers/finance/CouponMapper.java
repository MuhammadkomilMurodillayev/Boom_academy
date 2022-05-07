package team.bahor.mappers.finance;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import team.bahor.dto.finance.CouponCreateDto;
import team.bahor.dto.finance.CouponDto;
import team.bahor.dto.finance.CouponUpdateDto;
import team.bahor.entity.finance.Coupon;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CouponMapper extends AbstractMapper<
        Coupon,
        CouponDto,
        CouponCreateDto,
        CouponUpdateDto> {


    @Override
    CouponDto toDto(Coupon entity);

    @Override
    List<CouponDto> toDto(List<Coupon> entities);

    @Override
    Coupon fromCreateDto(CouponCreateDto createDto);

    @Override
    Coupon fromUpdateDto(CouponUpdateDto updateDto);
}
