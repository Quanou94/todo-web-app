package ch.cern.todo.service;

import ch.cern.todo.entity.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);

    User saveUser(User user);

    void deleteUser(Long id);

    List<User> getUsers();
}
