package com.simpleapp.email_app.app_user.registration.token;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepo confirmationTokenRepo;

    public void saveConfirmationToken(ConfirmationToken token)
    {
        confirmationTokenRepo.save(token);
    }
}
