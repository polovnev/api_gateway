package com.polovnev.country.dao;

import com.polovnev.country.entity.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByRole(String role);
}
