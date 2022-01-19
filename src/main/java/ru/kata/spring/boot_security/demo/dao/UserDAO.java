package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    User findByUsername(String username);
    List<User> getAll();
    void save(User user);
    void delete(int id);
    void update(int id, User user);
    User findById(int id);

}
