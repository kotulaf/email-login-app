package com.simpleapp.email_app.app_user.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.simpleapp.email_app.app_user.AppUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @SequenceGenerator(name = "confirmation_token_sequence", sequenceName = "confirmation_token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createsAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmsAt;

    @ManyToOne
    @JoinColumn(nullable = false,
                name = "app_user_id")
    private AppUser appUser;

    public ConfirmationToken(String token, LocalDateTime createsAt, LocalDateTime expiresAt, AppUser appUser)
    {
        this.token = token;
        this.createsAt = createsAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
