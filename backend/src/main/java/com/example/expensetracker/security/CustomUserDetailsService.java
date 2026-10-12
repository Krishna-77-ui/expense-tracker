package com.example.expensetracker.security;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository users;
 public CustomUserDetailsService(UserRepository users){this.users=users;}
 @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
  var user=users.findByEmailIgnoreCase(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
  return User.withUsername(user.getEmail()).password(user.getPassword()).roles("USER").build();
 }
}
