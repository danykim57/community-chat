package com.chat.community.core.model.user;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
@Table(name="\"user\"")
public class User extends Common{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String name;

    private String password;

    @Embedded
    private final Email email;

    @Embedded
    private final PhoneNumber phoneNumber;

    private LocalDateTime lastLoginTime;

    private LocalDateTime createTime;

    @Builder
    public User(String name, Email email, PhoneNumber phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Builder
    public User(Long id, String name, String password, Email email, PhoneNumber phoneNumber, LocalDateTime lastLoginTime, LocalDateTime createTime) {
        checkArgument(isNotEmpty(name), "Name cannot be empty");
        checkArgument(name.length() <= 750, "Name should be characters");
        checkArgument(isNotEmpty(password), "Password cannot be empty");
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lastLoginTime = lastLoginTime;
        this.createTime = defaultIfNull(createTime, now());
    }

    public void login(PasswordEncoder passwordEncoder, String credentials) {
        if (!passwordEncoder.matches(password, credentials)) {
            throw new IllegalArgumentException("Wrong password");
        }
    }

    public void afterLogin() {
        lastLoginTime = now();
    }

}
