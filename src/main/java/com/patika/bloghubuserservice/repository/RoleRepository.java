package com.patika.bloghubuserservice.repository;

import com.patika.bloghubuserservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
