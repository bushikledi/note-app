package com.project.noteapp.services;

import com.project.noteapp.authentication.AuthenticationRequest;
import com.project.noteapp.authentication.AuthenticationResponse;
import com.project.noteapp.configuration.PasswordEncoder;
import com.project.noteapp.model.Role;
import com.project.noteapp.model.User;
import com.project.noteapp.repository.NoteRepository;
import com.project.noteapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServicesImplementation implements UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final NoteRepository noteRepository;

    @Override
    public AuthenticationResponse saveUser(User user) {
        user.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(
            AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        User user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow();
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(token)
                .build();
    }

    @Override
    @Transactional
    public void updateUser(User user, User modUser) {
        if (modUser.getLastname() != null)
            user.setLastname(modUser.getLastname());
        if (modUser.getPhoto() != null)
            user.setPhoto(modUser.getPhoto());
        if (modUser.getFirstname() != null)
            user.setFirstname(modUser.getFirstname());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        user.getNotes().forEach(note -> noteRepository.deleteById(note.getNoteId()));
        userRepository.deleteById(user.getUserId());
    }

}
