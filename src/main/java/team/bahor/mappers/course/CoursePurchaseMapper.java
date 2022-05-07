package team.bahor.mappers.course;


import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import team.bahor.dto.course.purchase.CoursePurchaseCreateDto;
import team.bahor.dto.course.purchase.CoursePurchaseDto;
import team.bahor.dto.course.purchase.CoursePurchaseUpdateDto;
import team.bahor.entity.courses.CoursePurchaseHistory;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CoursePurchaseMapper extends AbstractMapper<
        CoursePurchaseHistory,
        CoursePurchaseDto,
        CoursePurchaseCreateDto,
        CoursePurchaseUpdateDto> {

    @Override
    CoursePurchaseDto toDto(CoursePurchaseHistory entity);

    @Override
    List<CoursePurchaseDto> toDto(List<CoursePurchaseHistory> entities);

    @Override
    CoursePurchaseHistory fromCreateDto(CoursePurchaseCreateDto createDto);

    @Override
    CoursePurchaseHistory fromUpdateDto(CoursePurchaseUpdateDto updateDto);
}
