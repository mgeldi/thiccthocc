package de.mgeldi.thiccthocc.repository;

import de.mgeldi.thiccthocc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    void deleteUserById(UUID uuid);
}
