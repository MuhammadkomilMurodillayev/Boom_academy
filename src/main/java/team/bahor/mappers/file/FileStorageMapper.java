package team.bahor.mappers.file;

import org.mapstruct.Mapper;
import team.bahor.dto.file.FileStorageCreateDto;
import team.bahor.dto.file.FileStorageDto;
import team.bahor.dto.file.FileStorageUpdateDto;
import team.bahor.entity.file.FileStorage;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileStorageMapper extends AbstractMapper<
        FileStorage,
        FileStorageDto,
        FileStorageCreateDto,
        FileStorageUpdateDto> {

    @Override
    FileStorageDto toDto(FileStorage entity);

    @Override
    List<FileStorageDto> toDto(List<FileStorage> entities);

    @Override
    FileStorage fromCreateDto(FileStorageCreateDto createDto);

    @Override
    FileStorage fromUpdateDto(FileStorageUpdateDto updateDto);
}
