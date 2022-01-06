package ru.otus.socialnetwork.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.socialnetwork.dto.User;

import java.sql.PreparedStatement;
import java.util.*;

@Component
@Transactional
@RequiredArgsConstructor
public class UserDao {

  private final JdbcTemplate jdbcTemplate;

  private final UserMapper userMapper;

  public Long save(User user) {
    String insertSql = "INSERT INTO user (email, password, first_name, last_name, age, sex, hobby, city) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{User.Fields.id});
      ps.setString(1, user.getEmail());
      ps.setString(2, user.getPassword());
      ps.setString(3, user.getFirstName());
      ps.setString(4, user.getLastName());
      ps.setInt(5, user.getAge());
      ps.setString(6, user.getSex() == null ? null : user.getSex().name());
      ps.setString(7, user.getHobby());
      ps.setString(8, user.getCity());
      return ps;
    }, keyHolder);
    return keyHolder.getKey().longValue();
  }

  public Optional<User> findByEmail(String username) {
    return Optional.ofNullable(
        jdbcTemplate.queryForObject("SELECT * FROM user WHERE email = ?", userMapper, username)
    );
  }

  public List<User> findAll() {
    return jdbcTemplate.query("SELECT * FROM user", userMapper);
  }

  public Optional<User> findById(Long id) {
    User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", userMapper, id);
    if (user != null) {
      user.setFriends(new HashSet<>(
          jdbcTemplate.query("SELECT * FROM user_friend as uf " +
              "INNER JOIN user on uf.friend_id = user.id WHERE user_id = ?", userMapper, id)
      ));
    }
    return Optional.ofNullable(user);
  }

  public void makeFriends(Long userId, Long friendId) {
    insertFriend(userId, friendId);
    insertFriend(friendId, userId);
  }

  private void insertFriend(Long userId, Long friendId) {
    jdbcTemplate.update("INSERT INTO user_friend (user_id, friend_id) VALUES (?, ?)", userId, friendId);
  }

  public void removeFromFriend(Long userId, Long friendId) {
    deleteFriend(userId, friendId);
    deleteFriend(friendId, userId);
  }

  private void deleteFriend(Long userId, Long friendId) {
    jdbcTemplate.update("DELETE FROM user_friend WHERE user_id = ? and friend_id = ? ", userId, friendId);
  }
}
