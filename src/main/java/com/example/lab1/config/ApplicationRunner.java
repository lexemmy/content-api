package com.example.lab1.config;

import com.example.lab1.model.Role;
import com.example.lab1.model.User;
import com.example.lab1.repository.RoleRepository;
import com.example.lab1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = new Role("ADMIN");
        roleRepository.save(adminRole);


        userRepository.save(new User("John", "j@mail.com", encoder.encode("password"), List.of(adminRole)));

    }
}
