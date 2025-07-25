package com.library.user.domain.port.in;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.library.user.domain.model.User;

public interface UserService {

    Mono<User> createUser(User user);
    Mono<User> getUserById(Long id);
    Flux<User> getAllUsers();
    Mono<User> updateUser(Long id, User user);
    Mono<Void> deleteUser(Long id); 
}
