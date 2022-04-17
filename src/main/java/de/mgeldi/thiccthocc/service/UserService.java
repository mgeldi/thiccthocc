package de.mgeldi.thiccthocc.service;

import de.mgeldi.thiccthocc.exceptions.UserNotFoundException;
import de.mgeldi.thiccthocc.model.User;
import de.mgeldi.thiccthocc.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(@Valid User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(@Valid User user) {
        return userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteUserById(userId);
    }

    public User findUserById(UUID userId) throws UserNotFoundException {
        return userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException("User by id" + userId + "was not found!"));
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User with the username '" + username + "' was not found!"));
    }

    public void deleteUserByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }
}
