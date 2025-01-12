package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }
    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}