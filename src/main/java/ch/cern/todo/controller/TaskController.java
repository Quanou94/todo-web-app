package ch.cern.todo.controller;

import ch.cern.todo.entity.Task;
import ch.cern.todo.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {
    TaskService taskService;

    @GetMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<Task> getTask(@PathVariable Long userId, @PathVariable Long categoryId) {
        return new ResponseEntity<>(this.taskService.getTask(userId, categoryId), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<Task> saveTask(@Valid @RequestBody Task task, @PathVariable Long userId, @PathVariable Long categoryId) {
        return new ResponseEntity<>(this.taskService.saveTask(task, userId, categoryId), HttpStatus.CREATED);
    }


    @PutMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task, @PathVariable Long userId, @PathVariable Long categoryId) {
        Task taskToUpdate = this.taskService.getTask(userId, categoryId);

        if (taskToUpdate.getName() != null) {
            taskToUpdate.setName(task.getName());
        }

        if (taskToUpdate.getDescription() != null) {
            taskToUpdate.setDescription(task.getDescription());
        }

        if (taskToUpdate.getDeadline() != null) {
            taskToUpdate.setDeadline(task.getDeadline());
        }

        return new ResponseEntity<>(this.taskService.saveTask(taskToUpdate, userId, categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long userId, @PathVariable Long categoryId) {
        this.taskService.deleteTask(userId, categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getStudentTask(@PathVariable Long userId) {
        return new ResponseEntity<>(this.taskService.getUserTasks(userId), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Task>> getCategoryTask(@PathVariable Long categoryId) {
        return new ResponseEntity<>(this.taskService.getTasksPerCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getTask() {
        return new ResponseEntity<>(this.taskService.getAllTasks(), HttpStatus.OK);
    }
}