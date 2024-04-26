package com.example.casestudylibrary.repository.user;

import com.example.casestudylibrary.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository  extends JpaRepository<Role, Long> {
}
