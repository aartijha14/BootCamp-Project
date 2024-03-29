package com.ttn.project2.security;


import com.ttn.project2.Model.User;
import com.ttn.project2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    UserRepository userRepository;

    public AppUser loadUserByEmail(String email) {

        User user = userRepository.findByEmailIgnoreCase(email);

        System.out.println(user);

        if (user != null) {
            return new AppUser(user.getEmail(), user.getPassword(), user.getRoleList(), user.isAccountNonExpired(), user.isAccountNonLocked(), user.isCredentialsNonExpired(), user.isEnabled());
        } else {
            throw new RuntimeException();
        }

    }
}
