package com.ttn.project2.security;


import com.ttn.project2.Model.User;
import com.ttn.project2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails requestedUser = userDao.loadUserByEmail(email);

        if (requestedUser != null) {

            Optional<User> user = userRepository.findByEmail(email);

            /*if (!user.isActive())
                throw new RuntimeException("The account is not active, please get it activated first.");

            if (user.isDeleted())
                throw new RuntimeException("The account is Deleted.");*/

            return requestedUser;

        } else {
            throw new UsernameNotFoundException("No user found with email : " + email);
        }






    }
}