package ch.cern.todo.controller;

import ch.cern.todo.entity.Task;
import ch.cern.todo.entity.User;
import ch.cern.todo.enums.Role;
import ch.cern.todo.service.TaskService;
import ch.cern.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    TaskService taskService;

    UserService userService;

    @GetMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<Task> getTask(@PathVariable Long userId, @PathVariable Long categoryId) {
        User currentUser = this.getCurrentUser();

        if (currentUser.getId().equals(userId) || currentUser.getRole() == Role.ADMIN) {
            return new ResponseEntity<>(this.taskService.getTask(userId, categoryId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<Task> saveTask(@Valid @RequestBody Task task, @PathVariable Long userId, @PathVariable Long categoryId) {
        User currentUser = this.getCurrentUser();

        if (currentUser.getId().equals(userId) || currentUser.getRole() == Role.ADMIN) {
            return new ResponseEntity<>(this.taskService.saveTask(task, userId, categoryId), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @PutMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task, @PathVariable Long userId, @PathVariable Long categoryId) {
        User currentUser = this.getCurrentUser();

        if (currentUser.getId().equals(userId) || currentUser.getRole() == Role.ADMIN) {
            Task taskToUpdate = this.taskService.getTask(userId, categoryId);
            taskToUpdate.setName(task.getName());
            taskToUpdate.setDescription(task.getDescription());
            if (taskToUpdate.getDeadline() != null) {
                taskToUpdate.setDeadline(task.getDeadline());
            }

            return new ResponseEntity<>(this.taskService.saveTask(taskToUpdate, userId, categoryId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long userId, @PathVariable Long categoryId) {
        User currentUser = this.getCurrentUser();
        if (currentUser.getId().equals(userId) || currentUser.getRole() == Role.ADMIN) {
            this.taskService.deleteTask(userId, categoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long userId) {
        User currentUser = this.getCurrentUser();
        if (currentUser.getId().equals(userId) || currentUser.getRole() == Role.ADMIN) {
            return new ResponseEntity<>(this.taskService.getUserTasks(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Task>> getCategoryTask(@PathVariable Long categoryId) {
        User currentUser = this.getCurrentUser();
        if (currentUser.getRole() == Role.ADMIN) {
            return new ResponseEntity<>(this.taskService.getTasksPerCategory(categoryId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getTask() {
        User currentUser = this.getCurrentUser();
        if (currentUser.getRole() == Role.ADMIN) {
            return new ResponseEntity<>(this.taskService.getAllTasks(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return this.userService.getUserByUsername(username);
        }
        return null;
    }
}