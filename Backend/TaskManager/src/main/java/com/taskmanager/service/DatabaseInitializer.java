package com.taskmanager.service;

import com.taskmanager.entity.ERole;
import com.taskmanager.entity.Role;
import com.taskmanager.entity.User;
import com.taskmanager.repository.RoleRepository;
import com.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        // Seed Roles
        if (roleRepository.findByName(ERole.ROLE_MANAGER).isEmpty()) {
            roleRepository.save(new Role(null, ERole.ROLE_MANAGER));
        }
        if (roleRepository.findByName(ERole.ROLE_DEVELOPER).isEmpty()) {
            roleRepository.save(new Role(null, ERole.ROLE_DEVELOPER));
        }

        // Seed Manager User
        String managerEmail = "manager@gmail.com";
        if (userRepository.findByEmail(managerEmail).isEmpty()) {
            Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER).orElseThrow();
            Set<Role> roles = new HashSet<>();
            roles.add(managerRole);

            User manager = User.builder()
                    .email(managerEmail)
                    .password(encoder.encode("Manager@123"))
                    .fullName("Manager")
                    .roles(roles)
                    .build();
            userRepository.save(manager);
        }
    }
}
