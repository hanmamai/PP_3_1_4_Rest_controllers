package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    Role getByName (String name);
    void save (Role role);
    List<Role> getAll();
    void deleteById(Long id);
    List<Role> findAllRoles();
}