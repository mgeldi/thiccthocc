package de.mgeldi.thiccthocc.controller;

import de.mgeldi.thiccthocc.exceptions.KeyboardProfileNotFoundException;
import de.mgeldi.thiccthocc.exceptions.UserNotFoundException;
import de.mgeldi.thiccthocc.model.KeyboardProfile;
import de.mgeldi.thiccthocc.model.User;
import de.mgeldi.thiccthocc.service.KeyboardProfileService;
import de.mgeldi.thiccthocc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/keyboardprofile")
public class KeyboardProfileController {
    private final KeyboardProfileService keyboardProfileService;

    public KeyboardProfileController(KeyboardProfileService keyboardProfileService) {
        this.keyboardProfileService = keyboardProfileService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<List<KeyboardProfile>> getAllKeyboardProfilesForUser(@PathVariable UUID id) {
        try {
            List<KeyboardProfile> keyboardProfiles = keyboardProfileService.findAllKeyboardProfilesForUser(id);
            return new ResponseEntity<>(keyboardProfiles, HttpStatus.NOT_FOUND);
        } catch (KeyboardProfileNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<KeyboardProfile> addKeyboardProfile(@RequestBody KeyboardProfile keyboardProfile) {
        KeyboardProfile newKeyboardProfile = keyboardProfileService.addKeyboardProfile(keyboardProfile);
        return new ResponseEntity<>(newKeyboardProfile, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<KeyboardProfile> updateUser(@RequestBody KeyboardProfile keyboardProfile) {
        KeyboardProfile newKeyboardProfile = keyboardProfileService.updateKeyboardProfile(keyboardProfile);
        return new ResponseEntity<>(newKeyboardProfile, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        keyboardProfileService.deleteKeyboardProfile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
