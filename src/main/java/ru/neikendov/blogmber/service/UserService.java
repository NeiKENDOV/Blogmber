package ru.neikendov.blogmber.service;


import ru.neikendov.blogmber.models.AppUser;

import java.util.List;

public interface UserService {
    List<AppUser> loadAllAppUsers();
    AppUser loadAppUserByUsername(String username) throws Exception;
    void addUser(
            String username,
            String password
    ) throws Exception;
}
