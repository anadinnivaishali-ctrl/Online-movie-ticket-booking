package com.jsp.book.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.book.entity.User;
import com.jsp.book.entity.enums.Role;
import com.jsp.book.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminRegistration implements CommandLineRunner {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {

        if (userRepository.existsByEmail(adminEmail)) {
            log.info("Startup initialization complete");
            return;
        }

        User adminUser = User.builder()
                .name("ADMIN")
                .email(adminEmail)
                .mobile(0L)
                .password(passwordEncoder.encode(adminPassword))
                .role(Role.ADMIN.name())
                .blocked(false)
                .build();

        userRepository.save(adminUser);
        log.info("Initial admin setup completed");
    }
}