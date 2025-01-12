package ru.kata.spring.boot_security.demo.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.util.List;
import java.util.Set;

@Component
public class DBInit  implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleService roleService;
    private final UserService userService;
    @Autowired
    public DBInit(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role adminRole = new Role("ROLE_ADMIN");
        if (roleService.getByName("ROLE_ADMIN") == null) {
            roleService.save(adminRole);
        }
        Role userRole = new Role("ROLE_USER");
        if (roleService.getByName("ROLE_USER") == null) {
            roleService.save(userRole);
        }
        if (userService.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setEmail("admin@mail.ru");
            admin.setRoles(Set.of(adminRole));
            userService.saveUser(admin);
        }
        if (userService.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("user");
            user.setEmail("user@mail.ru");
            user.setRoles(Set.of(userRole));
            userService.saveUser(user);
        }
    }
}