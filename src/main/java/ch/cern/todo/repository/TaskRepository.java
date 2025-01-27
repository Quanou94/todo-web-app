package ch.cern.todo.repository;

import ch.cern.todo.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findByUserIdAndCategoryId(Long userId, Long categoryId);
    List<Task> findByUserId(Long userId);
    List<Task> findByCategoryId(Long categoryId);
    @Transactional
    void deleteByUserIdAndCategoryId(Long userId, Long categoryId);
}
