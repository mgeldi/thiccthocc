package de.mgeldi.thiccthocc.service;

import de.mgeldi.thiccthocc.model.User;
import de.mgeldi.thiccthocc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        user.setUuid(UUID.randomUUID());
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(UUID userId){
        userRepository.deleteUserById(userId);
    }
}
