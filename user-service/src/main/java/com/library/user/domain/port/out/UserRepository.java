package com.library.user.domain.port.out;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.library.user.domain.model.User;

public interface UserRepository {

    Mono<User> findById(Long id);
    Mono<User> findByEmail(String email);
    Mono<User> save(User user);
    Mono<Void> deleteById(Long id);
    Flux<User> findAll();
    Mono<Boolean> existsByEmail(String email);
}   
