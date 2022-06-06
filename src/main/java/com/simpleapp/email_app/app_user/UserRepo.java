package com.simpleapp.email_app.app_user;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional(readOnly = true)
public interface UserRepo {
    Optional<AppUser> findByEmail(String email);
}
