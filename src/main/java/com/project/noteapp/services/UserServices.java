package com.project.noteapp.services;

import com.project.noteapp.model.User;
import com.project.noteapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServices {

    private UserRepository userRepository;

    public void newUser(User user) {
        userRepository.save(user);
    }

    public void editUser(User user) {
        userRepository.save(user);
    }

    public boolean deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public User getUserById(Integer id){
        return userRepository.findById(id).get();
    }
}
