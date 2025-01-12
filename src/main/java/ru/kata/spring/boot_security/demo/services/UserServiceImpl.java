package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional(readOnly = true)
    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Transactional(readOnly = true)
    @Override
    public User showUser(Long id) {
        return userRepository.getById(id);
    }
    @Transactional
    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        if (user.getPassword().length() == 0) {
            user.setPassword(userRepository.getById(user.getId()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
