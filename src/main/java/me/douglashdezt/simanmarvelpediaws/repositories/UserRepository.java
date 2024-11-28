package me.douglashdezt.simanmarvelpediaws.repositories;

import me.douglashdezt.simanmarvelpediaws.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
