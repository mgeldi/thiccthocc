package de.mgeldi.thiccthocc.service;

import de.mgeldi.thiccthocc.exceptions.KeyboardProfileNotFoundException;
import de.mgeldi.thiccthocc.exceptions.KeyboardProfileWithNameAlreadyExistsException;
import de.mgeldi.thiccthocc.model.KeyboardProfile;
import de.mgeldi.thiccthocc.model.User;
import de.mgeldi.thiccthocc.repository.KeyboardProfileRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KeyboardProfileService {

    private final KeyboardProfileRepository keyboardProfileRepository;

    public KeyboardProfileService(KeyboardProfileRepository keyboardProfileRepository) {
        this.keyboardProfileRepository = keyboardProfileRepository;
    }

    @Transactional
    public KeyboardProfile addKeyboardProfile(KeyboardProfile keyboardProfile) throws KeyboardProfileWithNameAlreadyExistsException {
        Optional<List<KeyboardProfile>> keyboardProfilesForOwner = keyboardProfileRepository.findAllByOwner(keyboardProfile.getOwner());
        if (keyboardProfilesForOwner.isPresent()) {
            boolean found = keyboardProfilesForOwner.get().stream().anyMatch(keyboardProfile1 ->
                    keyboardProfile1.getProfileName().equals(keyboardProfile.getProfileName()));
            if (found)
                throw new KeyboardProfileWithNameAlreadyExistsException("User " + keyboardProfile.getOwner().getUsername()
                        + "already has a profile called " + keyboardProfile.getProfileName());
        }

        keyboardProfile.setKeyboardProfileId(UUID.randomUUID());
        return keyboardProfileRepository.save(keyboardProfile);
    }

    public List<KeyboardProfile> findAllKeyboardProfilesForUser(User user) throws KeyboardProfileNotFoundException {
        return keyboardProfileRepository.findAllByOwner(user).orElseThrow(() -> new KeyboardProfileNotFoundException("User " + user.getUsername() + " has no Keyboard profiles!"));
    }

    @Transactional
    public void deleteKeyboardProfile(User user, String profileName) {
        keyboardProfileRepository.deleteKeyboardProfileByOwnerAndProfileName(user, profileName);
    }

    @Transactional
    public KeyboardProfile updateKeyboardProfile(KeyboardProfile keyboardProfile) throws KeyboardProfileNotFoundException {
        if (keyboardProfileRepository.existsById(keyboardProfile.getKeyboardProfileId()))
            throw new KeyboardProfileNotFoundException("Profile with id " + keyboardProfile.getKeyboardProfileId() + "does not exist. Cannot update!");
        return keyboardProfileRepository.save(keyboardProfile);
    }
}
