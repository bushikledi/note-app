package com.project.noteapp.services;

import com.project.noteapp.authentication.AuthenticationRequest;
import com.project.noteapp.authentication.AuthenticationResponse;
import com.project.noteapp.model.User;

public interface UserServices {
    AuthenticationResponse saveUser(User user);

    void editUser(User user);

    void updateUser(Integer id, User user);

    boolean deleteUser(Integer id);

    User getUserById(Integer id);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
