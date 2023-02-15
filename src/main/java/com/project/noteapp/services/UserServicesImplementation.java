package com.project.noteapp.services;

import com.project.noteapp.configuration.PasswordEncoder;
import com.project.noteapp.model.User;
import com.project.noteapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServicesImplementation implements UserServices {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void newUser(User user) {
        user.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, User user) {
        user.setUserId(id);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }
}
