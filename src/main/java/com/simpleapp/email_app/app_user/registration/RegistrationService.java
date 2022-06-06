package com.simpleapp.email_app.app_user.registration;

import org.springframework.stereotype.Service;

import com.simpleapp.email_app.app_user.AppUser;
import com.simpleapp.email_app.app_user.AppUserRole;
import com.simpleapp.email_app.app_user.AppUserService;
import com.simpleapp.email_app.app_user.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("email not valid");
        }
        String token = appUserService.signUpUser(new AppUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), AppUserRole.USER));

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(), buildEmail(request.getFirstName(), link));

        return token;
    }
    
}
