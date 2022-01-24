package ru.neikendov.blogmber.service.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.neikendov.blogmber.models.AppUser;
import ru.neikendov.blogmber.models.Role;
import ru.neikendov.blogmber.repos.UserRepo;
import ru.neikendov.blogmber.service.UserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findAppUserByUsername(username);
    }

    @Override
    public List<AppUser> loadAllAppUsers() {
        return userRepo.findAll();
    }

    @Override
    public AppUser loadAppUserByUsername(String username) throws Exception {
         AppUser appUser = userRepo.findAppUserByUsername(username);
         if (appUser == null) {
             throw new NotFoundException("user: " + username + " not found");
         } else {
             return appUser;
         }
    }

    @Override
    public void addUser(String username, String password) throws Exception {
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(password);
        appUser.setRoles(Collections.singleton(Role.USER));
        appUser.setLikedPosts(new HashSet<>());
        appUser.setActive(true);
        userRepo.saveAndFlush(appUser);
        if (!userRepo.existsById(appUser.getId())) {
            throw new Exception("Error to save user");
        }

    }
}
