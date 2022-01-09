package ru.neikendov.blogmber.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neikendov.blogmber.models.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);
}
