package ch.cern.todo;

import ch.cern.todo.entity.Category;
import ch.cern.todo.entity.User;
import ch.cern.todo.enums.Role;
import ch.cern.todo.repository.CategoryRepository;
import ch.cern.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
}
