package ch.cern.todo.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long userId, Long categoryId) {
        super("The task with user id " + userId + " and category id: " + categoryId + " does not exist");
    }
}
