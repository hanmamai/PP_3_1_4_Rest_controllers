package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // Метод для получения всех пользователей
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // Метод для получения всех ролей
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.findAllRoles();
    }

    // Метод для сохранения нового пользователя
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    // Метод для обновления пользователя
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUserOptional = userService.findUserById(id);
        if (existingUserOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = existingUserOptional.get();
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRoles(updatedUser.getRoles());
        userService.updateUser(existingUser);

        return ResponseEntity.ok(existingUser);
    }
}