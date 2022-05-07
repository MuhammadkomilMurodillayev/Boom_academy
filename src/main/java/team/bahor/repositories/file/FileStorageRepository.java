package team.bahor.repositories.file;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.bahor.entity.file.FileStorage;
import team.bahor.repositories.base.AbstractRepository;

import java.util.Optional;

@Repository
public interface FileStorageRepository extends AbstractRepository<FileStorage, String> {

    Optional<FileStorage> findByGeneratedNameAndDeletedFalse(String generatedName);

    Optional<FileStorage> findByGeneratedNameAndDeletedAndCreatedBy(String name, boolean deleted, String createdBy);

    Optional<FileStorage> findByLessonIdAndTypeAndDeletedFalse(String lessonId, String type);

}
