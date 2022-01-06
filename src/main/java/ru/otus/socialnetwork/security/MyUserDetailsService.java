package ru.otus.socialnetwork.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.socialnetwork.dao.UserDao;
import ru.otus.socialnetwork.dto.User;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

  private final UserDao dao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = dao.findByEmail(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    return new MyUserDetails(user.get());
  }
}