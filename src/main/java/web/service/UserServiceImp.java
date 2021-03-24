package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public List<User> index() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User show(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.saveAndFlush(updatedUser);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
