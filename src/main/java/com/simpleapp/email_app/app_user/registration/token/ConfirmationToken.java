package com.simpleapp.email_app.app_user.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
    private LocalDateTime confirmedAt;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, LocalDateTime confirmedAt)
    {
        this.token = token;
        this.createsAt = createdAt;
        this.expiresAt = expiredAt;
        this.confirmedAt = confirmedAt;
    }
}
