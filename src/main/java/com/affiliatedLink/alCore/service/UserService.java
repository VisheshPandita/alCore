package com.affiliatedLink.alCore.service;

import com.affiliatedLink.alCore.entity.User;
import com.affiliatedLink.alCore.exception.UserNotFoundException;
import com.affiliatedLink.alCore.model.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getUserById(UUID productOwner) throws UserNotFoundException;

    List<User> getUsers();

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);
}
