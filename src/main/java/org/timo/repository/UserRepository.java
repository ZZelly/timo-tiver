package org.timo.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.timo.model.user.User;

import java.util.Optional;

public interface UserRepository extends CassandraRepository<User, String> {
    @AllowFiltering
    Optional<User> findByUsername(String username);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);
}