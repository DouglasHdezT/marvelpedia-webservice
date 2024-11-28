package me.douglashdezt.simanmarvelpediaws.services;

import me.douglashdezt.simanmarvelpediaws.dtos.SaveUserDTO;
import me.douglashdezt.simanmarvelpediaws.models.User;

public interface UserService {
    void createUser(SaveUserDTO info);

    User findUserByEmail(String email);
    User findAuthenticated();

    boolean verifyPassword(User user, String password);
}
