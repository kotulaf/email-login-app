package com.simpleapp.email_app.app_user.registration;

import org.springframework.stereotype.Service;

import com.simpleapp.email_app.app_user.AppUser;
import com.simpleapp.email_app.app_user.AppUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(new AppUser(request.getFirstName(), request.getLastName(), request.));
    }
    
}
