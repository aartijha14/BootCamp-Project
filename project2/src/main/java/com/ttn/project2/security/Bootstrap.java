package com.ttn.project2.security;


import com.ttn.project2.Model.User;
import com.ttn.project2.repository.CustomerRepository;
import com.ttn.project2.repository.RoleRepository;
import com.ttn.project2.repository.SellerRepository;
import com.ttn.project2.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

@Log4j2
@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    RoleRepository roleRepository;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() < 1) {


            addAdmin();


        }
    }


    private void addAdmin() {
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setMiddleName("Aarti");
        admin.setLastName("Jha");
        admin.setEmail("aartijha703@gmail.com");
        admin.setPassword(passwordEncoder.encode("1A2a$5AA"));
        admin.addRoles(roleRepository.findByAuthority("ROLE_ADMIN"));



        // explicitly setting because in constructor this is set as false
        admin.setActive(true);


        userRepository.save(admin);
    }

}