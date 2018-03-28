package com.chothuesach.repository;

import org.springframework.data.repository.CrudRepository;

import com.chothuesach.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findOneByRoleName(String roleName);
}
