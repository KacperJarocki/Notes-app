package server.auth;

import lombok.*;
import server.model.Role;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String userName;
    private String accountTypeName;
    private String urlToken;
    private Role role;
}
