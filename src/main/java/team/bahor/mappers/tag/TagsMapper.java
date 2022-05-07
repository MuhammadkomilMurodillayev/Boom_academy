package team.bahor.mappers.tag;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import team.bahor.dto.tag.TagCreateDto;
import team.bahor.dto.tag.TagDto;
import team.bahor.dto.tag.TagUpdateDto;
import team.bahor.entity.tag.Tags;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;


@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TagsMapper extends AbstractMapper<Tags, TagDto, TagCreateDto, TagUpdateDto> {
    @Override
    TagDto toDto(Tags entity);

    @Override
    List<TagDto> toDto(List<Tags> entities);

    @Override
    Tags fromCreateDto(TagCreateDto createDto);

    @Override
    Tags fromUpdateDto(TagUpdateDto updateDto);

    Tags fromUpdateDto(TagUpdateDto updateDto, @MappingTarget Tags tags);

}
