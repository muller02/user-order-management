package com.example.demo.config;

import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    final String USER = "ROLE_USER";
    final String ADMIN = "ROLE_ADMIN";
    final String EMAIL_ADMIN = "admin@demo.com";
    final String PASSWORD_ADMIN = "admin1234";

    // 초기 데이터 삽입
    // USER_ROLE 데이터
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role userRole = roleRepository.findByName(USER)
                .orElseGet(() -> roleRepository.save(new Role(null, USER)));
        Role adminRole = roleRepository.findByName(ADMIN)
                .orElseGet(() -> roleRepository.save(new Role(null, ADMIN)));

    // User 데이터 
    if(userRepository.findByEmail(EMAIL_ADMIN).isEmpty()) {
        userRepository.save(
            User.builder()
                .name("Admin User")
                .email(EMAIL_ADMIN)
                .password(PASSWORD_ADMIN) // TODO: 암호화 필요
                .roles(Set.of(adminRole, userRole))
                .providerId("local")
                .build()
            );
        log.info("==> 기본 권한 데이터 초기화 <==");
    }
    else
        log.info("==> 시딩 생략 <==");
    }
}