package team.bahor.dto.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import team.bahor.dto.BaseGenericDto;


@Getter
@Setter
public class FileStorageCreateDto implements BaseGenericDto {

    String lessonId;

    MultipartFile file;

}
