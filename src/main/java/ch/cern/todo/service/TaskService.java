package ch.cern.todo.service;

import ch.cern.todo.entity.Task;

import java.util.List;

public interface TaskService {
    Task getTask(Long userId, Long categoryId);
    Task saveTask(Task task, Long userId, Long categoryId);
    void deleteTask(Long userId, Long categoryId);
    List<Task> getUserTasks(Long userId);
    List<Task> getTasksPerCategory(Long categoryId);
    List<Task> getAllTasks();
}
