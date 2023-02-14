package com.project.noteapp.services;

import com.project.noteapp.model.User;

public interface UserServices {
    void newUser(User user);

    void editUser(User user);

    void updateUser(Integer id,User user);

    boolean deleteUser(Integer id);

    User getUserById(Integer id);
}
