package ru.otus.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.socialnetwork.dao.UserDao;
import ru.otus.socialnetwork.dto.User;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserDao dao;

  private final PasswordEncoder encoder;

  public Long registerNewUser(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    return dao.save(user);
  }

  public List<User> getUsers() {
    return dao.findAll();
  }

  public User getUser(Long id) {
    return dao.findById(id).orElseThrow();
  }

  public void makeFriends(Long userId, Long friendId) {
    dao.makeFriends(userId, friendId);
  }

  public void removeFromFriends(Long userId, Long friendId) {
    dao.removeFromFriend(userId, friendId);
  }
}
