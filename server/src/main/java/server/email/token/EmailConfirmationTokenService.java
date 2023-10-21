package server.email.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailConfirmationTokenService {

    private final EmailConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(EmailConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<EmailConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
