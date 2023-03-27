package com.affiliatedLink.alCore.event.listener;

import com.affiliatedLink.alCore.entity.User;
import com.affiliatedLink.alCore.event.RegistrationCompleteEvent;
import com.affiliatedLink.alCore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create verification token for user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        // send mail to user
        String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;
        // sendVerificationEmail()
        log.info("CLick the link to verify your account: {}", url);
    }
}
