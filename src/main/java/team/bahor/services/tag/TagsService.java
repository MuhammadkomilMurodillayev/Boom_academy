package team.bahor.services.tag;

import team.bahor.dto.tag.TagCreateDto;
import team.bahor.dto.tag.TagDto;
import team.bahor.dto.tag.TagUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface TagsService extends GenericCrudService<
        TagDto,
        TagCreateDto,
        TagUpdateDto,
        String> {
}
