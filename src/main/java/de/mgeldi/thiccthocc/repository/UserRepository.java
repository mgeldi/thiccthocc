package de.mgeldi.thiccthocc.repository;

import de.mgeldi.thiccthocc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    void deleteUserById(UUID uuid);
    Optional<User> findUserById(UUID uuid);

    Optional<User> findUserByUsername(String username);

    @Transactional
    void deleteUserByUsername(String username);
}
