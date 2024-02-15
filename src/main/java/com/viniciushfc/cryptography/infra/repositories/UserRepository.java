package com.viniciushfc.cryptography.infra.repositories;

import com.viniciushfc.cryptography.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
