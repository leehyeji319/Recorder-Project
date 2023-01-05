package com.recoder.backend.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recoder.backend.domain.user.Role;
import com.recoder.backend.domain.user.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}
