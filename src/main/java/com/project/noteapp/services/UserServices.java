package com.project.noteapp.services;

import com.project.noteapp.authentication.AuthenticationRequest;
import com.project.noteapp.authentication.AuthenticationResponse;
import com.project.noteapp.model.User;

public interface UserServices {
    AuthenticationResponse saveUser(User user);

    void updateUser(User user, User update);

    void deleteUser(User user);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
