package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService {


    List<User> getAll();

    User findById(int id);

    void save(User user, String role);


    void update(int id, User user, String role);

    void delete(int id);
}
