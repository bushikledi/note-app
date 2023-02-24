package com.project.noteapp.services;

import com.project.noteapp.authentication.AuthenticationRequest;
import com.project.noteapp.authentication.AuthenticationResponse;
import com.project.noteapp.configuration.PasswordEncoder;
import com.project.noteapp.model.Note;
import com.project.noteapp.model.Role;
import com.project.noteapp.model.User;
import com.project.noteapp.repository.NoteRepository;
import com.project.noteapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServicesImplementation implements UserServices {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, User user) {
        user.setUserId(id);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        List<Note> notes = noteRepository.findAll()
                .stream()
                .filter(note -> user.equals(note.getUser()))
                .toList();
        notes.forEach(note -> noteRepository.deleteById(note.getNoteId()));
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }
}
