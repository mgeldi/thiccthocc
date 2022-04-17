package de.mgeldi.thiccthocc.service;

import de.mgeldi.thiccthocc.exceptions.UserAlreadyExistsException;
import de.mgeldi.thiccthocc.exceptions.UserNotFoundException;
import de.mgeldi.thiccthocc.model.User;
import de.mgeldi.thiccthocc.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User addUser(@Valid User user) throws UserAlreadyExistsException {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent())
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists!");
        else if (userRepository.findUserByEmail(user.getEmail()).isPresent())
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists!");
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(@Valid User user) {
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User with the username '" + username + "' was not found!"));
    }

    @Transactional
    public void deleteUserByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }
}
