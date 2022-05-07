package team.bahor.mappers.base;

import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.GenericDto;
import team.bahor.entity.base.BaseGenericEntity;

import java.util.List;

public interface AbstractMapper<
        E extends BaseGenericEntity,
        D extends GenericDto,
        CD extends BaseGenericDto,
        UP extends  GenericDto
        > extends BaseGenericMapper{
    D toDto(E entity);

    List<D> toDto(List<E> entities);

    E fromCreateDto(CD createDto);

    E fromUpdateDto(UP updateDto);
}