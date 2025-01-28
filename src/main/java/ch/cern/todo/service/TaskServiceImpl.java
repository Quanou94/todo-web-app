package ch.cern.todo.service;

import ch.cern.todo.entity.Task;
import ch.cern.todo.entity.User;
import ch.cern.todo.exception.TaskNotFoundException;
import ch.cern.todo.repository.CategoryRepository;
import ch.cern.todo.repository.TaskRepository;
import ch.cern.todo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService{
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;


    @Override
    public Task getTask(Long userId, Long categoryId) {
        Optional<Task> task = this.taskRepository.findByUserIdAndCategoryId(userId, categoryId);
        return unwrapTask(task, userId, categoryId);
    }

    @Override
    public Task saveTask(Task task, Long userId, Long categoryId) {
        task.setUser(UserServiceImpl.unwrapUser(this.userRepository.findById(userId), userId));
        task.setCategory(CategoryServiceImpl.unwrapCategory(this.categoryRepository.findById(categoryId), userId));
        return this.taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long userId, Long categoryId) {
        this.taskRepository.deleteByUserIdAndCategoryId(userId, categoryId);
    }

    @Override
    public List<Task> getUserTasks(Long userId) {
        return this.taskRepository.findByUserId(userId);
    }

    @Override
    public List<Task> getTasksPerCategory(Long categoryId) {
        return this.taskRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) this.taskRepository.findAll();
    }

    private Task unwrapTask(Optional<Task> entity, Long userId, Long categoryId) {
        if (entity.isPresent()) return entity.get();
        else throw new TaskNotFoundException(userId, categoryId);
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return this.userRepository.findByUsername(username).orElse(null);
        }
        return null;
    }
}