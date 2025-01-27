package ch.cern.todo.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("The category id '" + id + "' does not exist");
    }

}
