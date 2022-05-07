package team.bahor.repositories.lesson;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.lesson.NotesToLessons;
import team.bahor.repositories.base.AbstractRepository;

import java.util.List;
import java.util.Optional;

public interface NotesToLessonsRepository extends AbstractRepository<NotesToLessons, String> {

    @Modifying
    @Query(value = "update boom_academy.main.notes_to_lessons set is_deleted = 1, updated_at = now() where id = ?1 and  is_deleted = 0", nativeQuery = true)
    void deleteFindById(String id);

    @Query(value = "select * from boom_academy.main.notes_to_lessons where id = ?1 and is_deleted = 0", nativeQuery = true)
    Optional<NotesToLessons> findByNotesToLessonsId(String id);

    @Query(value = "select * from boom_academy.main.notes_to_lessons where id = ?1 and created_by = ?2 and is_deleted = 0", nativeQuery = true)
    Optional<NotesToLessons> findByNotesToLessonsId(String id, String userId);

    @Query(value = "select * from boom_academy.main.notes_to_lessons where created_by = ?1 and is_deleted = 0", nativeQuery = true)
    List<NotesToLessons> findByAuthUserAllNotes(String userId);
}
