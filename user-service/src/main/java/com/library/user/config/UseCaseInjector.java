package com.library.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.library.user.application.UserServiceImpl;
import com.library.user.domain.port.in.UserService;
import com.library.user.domain.port.out.UserRepository;

@Configuration
public class UseCaseInjector {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }
}
