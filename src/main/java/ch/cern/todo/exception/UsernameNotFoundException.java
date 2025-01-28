package ch.cern.todo.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("The username " + username + "does not exist");
    }

}
