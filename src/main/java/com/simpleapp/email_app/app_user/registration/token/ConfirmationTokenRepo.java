package com.simpleapp.email_app.app_user.registration.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);
}
