package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.Users;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, String> {
    public Optional<Users> findByUsername(String username);
}

