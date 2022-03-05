package com.polovnev.api_gateway.dao;

import com.polovnev.api_gateway.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByUsername(String username);

    @Modifying
    @Query("UPDATE UserEntity u set u.isConfirmed = true WHERE u.activationCode = :activationCode AND u.username = :username")
    void activateUser(@Param(value = "username") String username,
                      @Param(value = "activationCode") String activationCode);
}
