package de.mgeldi.thiccthocc.repository;

import de.mgeldi.thiccthocc.model.KeyboardProfile;
import de.mgeldi.thiccthocc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KeyboardProfileRepository extends JpaRepository<KeyboardProfile, UUID> {
    Optional<List<KeyboardProfile>> findAllByKeyboardProfileId(UUID id);

    void deleteKeyboardProfileByKeyboardProfileId(UUID id);

    void deleteKeyboardProfileByOwnerAndProfileName(User user, String profileName);

    Optional<List<KeyboardProfile>> findAllByOwner(User user);
}
