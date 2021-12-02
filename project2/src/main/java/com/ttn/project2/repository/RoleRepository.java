package com.ttn.project2.repository;

import com.ttn.project2.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByAuthority(String role);
}
