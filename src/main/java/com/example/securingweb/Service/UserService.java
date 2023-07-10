package com.example.securingweb.Service;

import com.example.securingweb.Entity.Role;
import com.example.securingweb.Entity.User;
import com.example.securingweb.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean createUser(User user){
        User userDB = userRepository.findByUsername(user.getUsername());
        if (userDB != null){
            return false;
        }
        user.setActive(true);
        user.setVerification(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
//        User admin = new User();
//        admin.setVerification(true);
//        admin.setActive(true);
//        admin.setUsername("admin@mail.ru");
//        admin.setPassword(passwordEncoder.encode("password"));
//        admin.setRoles(Collections.singleton(Role.ADMIN));
//        userRepository.save(admin);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),user.getActive(),true, true, true, mapRoles(user.getRoles()));
    }

    private List<? extends GrantedAuthority> mapRoles(Set<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.getActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getUsername());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getUsername());
            }
            userRepository.save(user);
        }
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal){
        if (principal == null)
            return null;
        return userRepository.findByUsername(principal.getName());
    }

    public List<User> list() {
        return (List<User>) userRepository.findAll();
    }

    public void acceptUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null){

        }
    }
}
