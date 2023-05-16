package lt.Rolandas.basketball.service;

import lt.Rolandas.basketball.model.User;
import lt.Rolandas.basketball.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public Page<User> getAllUsers(int number) {
        return userRepository.findAll(PageRequest.of(number, 10));
    }
}
