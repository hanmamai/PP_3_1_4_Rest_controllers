package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Optional;


@Service
public interface UserService extends UserDetailsService {
    User findByUsername(String name);
    void saveUser(User user);
    User showUser(Long id);
    void removeUserById(Long id);
    List<User> getAllUsers();
    void updateUser(User user);
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
}