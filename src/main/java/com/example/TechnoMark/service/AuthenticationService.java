package com.example.TechnoMark.service;

import com.example.TechnoMark.model.AuthResponse;
import com.example.TechnoMark.model.Customer;
import com.example.TechnoMark.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomerRepository customerRepository;

    @Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, CustomerRepository customerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
    }

    public AuthResponse register(Customer customer) {
        Customer register = new Customer();

        register.setUsername(customer.getUsername());
        register.setPassword(passwordEncoder.encode(customer.getPassword()));
        register.setRole(customer.getRole());
        register.setEmail(customer.getEmail());
        register.setAddress(customer.getAddress());
        register.setPhoneNumber(customer.getPhoneNumber());

        register = customerRepository.save(customer);

        String token = jwtService.generateToken(register);

        return new AuthResponse("Register successfully", "Token :" + token);
    }

    public AuthResponse login(Customer customer) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword())
        );

        Customer custFromDb = customerRepository.findCustomerByUsername(customer.getUsername()).orElseThrow();
        String token = jwtService.generateToken(custFromDb);

        return new AuthResponse("Login successfully", "Token :" + token);
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }
}
