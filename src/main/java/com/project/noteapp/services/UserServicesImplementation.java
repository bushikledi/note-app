package com.project.noteapp.services;

import com.project.noteapp.model.User;
import com.project.noteapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServicesImplementation implements UserServices {

    private UserRepository userRepository;

    @Override
    public void newUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }
}
