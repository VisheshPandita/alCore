package com.affiliatedLink.alCore.service;

import com.affiliatedLink.alCore.entity.User;
import com.affiliatedLink.alCore.entity.VerificationToken;
import com.affiliatedLink.alCore.exception.UserNotFoundException;
import com.affiliatedLink.alCore.model.UserModel;
import com.affiliatedLink.alCore.repository.UserRepository;
import com.affiliatedLink.alCore.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User registerUser(UserModel userModel) {
        User user = new User(
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail(),
                passwordEncoder.encode(userModel.getPassword()),
                "USER"
        );

        return userRepository.save(user);
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    public User getUserById(UUID userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) return user.get();
        else throw new UserNotFoundException("User not found with id " + userId.toString());
    }
}
