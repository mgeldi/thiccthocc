package de.mgeldi.thiccthocc.service;

import de.mgeldi.thiccthocc.exceptions.KeyboardProfileNotFoundException;
import de.mgeldi.thiccthocc.model.KeyboardProfile;
import de.mgeldi.thiccthocc.repository.KeyboardProfileRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class KeyboardProfileService {

    private final KeyboardProfileRepository keyboardProfileRepository;

    public KeyboardProfileService(KeyboardProfileRepository keyboardProfileRepository) {
        this.keyboardProfileRepository = keyboardProfileRepository;
    }

    @Transactional
    public KeyboardProfile addKeyboardProfile(KeyboardProfile keyboardProfile) {
        keyboardProfile.setKeyboardProfileId(UUID.randomUUID());
        return keyboardProfileRepository.save(keyboardProfile);
    }

    public List<KeyboardProfile> findAllKeyboardProfilesForUser(UUID userId) throws KeyboardProfileNotFoundException {
        return keyboardProfileRepository.findAllByKeyboardProfileId(userId).orElseThrow(() -> new KeyboardProfileNotFoundException("User by id" + userId + "was not found!"));
    }

    @Transactional
    public void deleteKeyboardProfile(UUID id){
        keyboardProfileRepository.deleteKeyboardProfileByKeyboardProfileId(id);
    }

    @Transactional
    public KeyboardProfile updateKeyboardProfile(KeyboardProfile keyboardProfile){
        return keyboardProfileRepository.save(keyboardProfile);
    }
}
