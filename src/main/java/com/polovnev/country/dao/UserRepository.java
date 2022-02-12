package com.polovnev.country.dao;

import com.polovnev.country.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByUsername(String username);
}
