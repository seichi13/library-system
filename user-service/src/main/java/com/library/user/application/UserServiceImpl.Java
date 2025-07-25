package com.library.user.application;

import com.library.user.exceptions.EmailAlreadyExistsException;
import com.library.user.exceptions.UserNotFoundException;
import com.library.user.domain.model.User;
import com.library.user.domain.port.in.UserService;
import com.library.user.domain.port.out.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public record UserServiceImpl(UserRepository userRepository) implements UserService {

    @Override
    public Mono<User> createUser(User user) {
        return userRepository.existsByEmail(user.getEmail())
            .flatMap(exists -> {
                if (exists) {
                    return Mono.error(new EmailAlreadyExistsException("Email already exists: " + user.getEmail()));
                }
                return userRepository.save(user);
            });
    }

    @Override
    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id)
            .switchIfEmpty(Mono.error(new UserNotFoundException("User not found with id: " + id)));
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll()
            .switchIfEmpty(Mono.error(new UserNotFoundException("No users found")));
    }

    @Override
    public Mono<User> updateUser(Long id, User user) {
        return userRepository.findById(id)
            .switchIfEmpty(Mono.error(new UserNotFoundException("User not found with id: " + id)))
            .flatMap(existingUser -> {
                existingUser.setFirstname(user.getFirstname());
                existingUser.setLastname(user.getLastname());
                existingUser.setEmail(user.getEmail());
                existingUser.setPhoneNumber(user.getPhoneNumber());
                return userRepository.save(existingUser);
            });
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        return userRepository.findById(id)
            .switchIfEmpty(Mono.error(new UserNotFoundException("User not found with id: " + id)))
            .flatMap(user -> userRepository.deleteById(id))
            .then();
    }
}   