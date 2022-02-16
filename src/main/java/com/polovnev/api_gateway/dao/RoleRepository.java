package com.polovnev.api_gateway.dao;

import com.polovnev.api_gateway.entity.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByRole(String role);
}
