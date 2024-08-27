package com.example.RegisterLogin.config;

import com.example.RegisterLogin.Repo.EmployeeRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    private final EmployeeRepo employeeRepo;

    public ApplicationConfiguration(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Bean


    public UserDetailsService userDetailsService() {
        return identifier -> {
            // Determine if the identifier is an email or an ID
            if (isNumeric(identifier)) {
                // Handle as ID
                Integer employeeId = Integer.parseInt(identifier);
                return employeeRepo.findById(employeeId)
                        .orElseThrow(() -> new UsernameNotFoundException("Employee not found with ID: " + identifier));
            } else {
                // Handle as email
                return employeeRepo.findByEmail(identifier)
                        .orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: " + identifier));
            }
        };
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");  // This checks if the string contains only digits
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}