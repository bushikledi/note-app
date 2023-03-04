package com.project.noteapp.services;

import com.project.noteapp.authentication.AuthenticationRequest;
import com.project.noteapp.authentication.AuthenticationResponse;
import com.project.noteapp.configuration.PasswordEncoder;
import com.project.noteapp.model.Role;
import com.project.noteapp.model.User;
import com.project.noteapp.repository.NoteRepository;
import com.project.noteapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class UserServicesImplementation implements UserServices {

    private static final Logger logger = LoggerFactory.getLogger(UserServicesImplementation.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final NoteRepository noteRepository;

    @Override
    public AuthenticationResponse saveUser(User user) {
        try {
            user.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
            user.setRole(Role.USER);
            userRepository.save(user);
            String token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .jwtToken(token)
                    .build();
        } catch (DataAccessException e) {
            logger.error("Error saving user: {}", e.getMessage());
            throw new ServiceException("Error saving user");
        }

    }

    @Override
    public AuthenticationResponse authenticate(
            AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
            User user = userRepository.findByUsername(authenticationRequest.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
            String token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .jwtToken(token)
                    .build();
        } catch (AuthenticationException e) {
            logger.error("Error authenticating user: {}", e.getMessage());
            throw new AuthenticationException("Invalid username or password") {
            };
        }

    }

    @Override
    @Transactional
    public void updateUser(User user, User modUser) {
        try {
            if (StringUtils.hasText(modUser.getLastname())) {
                user.setLastname(modUser.getLastname());
            }
            if (StringUtils.hasText(modUser.getPhoto())) {
                user.setPhoto(modUser.getPhoto());
            }
            if (StringUtils.hasText(modUser.getFirstname())) {
                user.setFirstname(modUser.getFirstname());
            }
            userRepository.save(user);
        } catch (DataAccessException e) {
            logger.error("Error updating user: {}", e.getMessage());
            throw new ServiceException("Error updating user");
        }
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        try {
            user.getNotes().forEach(note -> noteRepository.deleteById(note.getNoteId()));
            userRepository.deleteById(user.getUserId());
        } catch (DataAccessException e) {
            logger.error("Error deleting user: {}", e.getMessage());
            throw new ServiceException("Error deleting user");

        }
    }


}
