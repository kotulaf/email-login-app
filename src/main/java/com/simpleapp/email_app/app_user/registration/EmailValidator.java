package com.simpleapp.email_app.app_user.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String arg0) {
       // TODO: Regex to validate email
        return true;
    }
    
}
