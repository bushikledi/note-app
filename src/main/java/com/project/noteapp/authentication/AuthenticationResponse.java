package com.project.noteapp.authentication;

import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwtToken;
}
