package de.mgeldi.thiccthocc.controller;

import de.mgeldi.thiccthocc.exceptions.KeyboardProfileNotFoundException;
import de.mgeldi.thiccthocc.exceptions.KeyboardProfileWithNameAlreadyExistsException;
import de.mgeldi.thiccthocc.exceptions.UserNotFoundException;
import de.mgeldi.thiccthocc.model.KeyboardProfile;
import de.mgeldi.thiccthocc.service.KeyboardProfileService;
import de.mgeldi.thiccthocc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/keyboardprofile")
public class KeyboardProfileController {
    private final KeyboardProfileService keyboardProfileService;
    private final UserService userService;

    public KeyboardProfileController(KeyboardProfileService keyboardProfileService, UserService userService) {
        this.keyboardProfileService = keyboardProfileService;
        this.userService = userService;
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<List<KeyboardProfile>> getAllKeyboardProfilesForUser(@PathVariable String username) throws UserNotFoundException, KeyboardProfileNotFoundException {
        List<KeyboardProfile> keyboardProfiles = keyboardProfileService.findAllKeyboardProfilesForUser(userService.findUserByUsername(username));
        return new ResponseEntity<>(keyboardProfiles, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addKeyboardProfile(@Valid @RequestBody KeyboardProfile keyboardProfile) throws KeyboardProfileWithNameAlreadyExistsException {
        KeyboardProfile newKeyboardProfile = keyboardProfileService.addKeyboardProfile(keyboardProfile);
        return new ResponseEntity<>(newKeyboardProfile, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<KeyboardProfile> updateKeyboardProfile(@Valid @RequestBody KeyboardProfile keyboardProfile) throws KeyboardProfileNotFoundException {
        KeyboardProfile newKeyboardProfile = keyboardProfileService.updateKeyboardProfile(keyboardProfile);
        return new ResponseEntity<>(newKeyboardProfile, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{username}/{profileName}")
    public ResponseEntity<?> deleteKeyboardProfile(@PathVariable String username, @PathVariable String profileName) throws UserNotFoundException {
        keyboardProfileService.deleteKeyboardProfile(userService.findUserByUsername(username), profileName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
