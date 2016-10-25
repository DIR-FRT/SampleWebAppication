package fpt.dir.sampleweb.service;

import fpt.dir.sampleweb.model.AppUser;
import fpt.dir.sampleweb.model.Role;
import fpt.dir.sampleweb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

/**
 * This class implements UserDetailsService
 * to check permission when user login
 *
 * @author HoangNTT
 * @version 1.0
 * @since 2016-08-20
 */
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  /**
   * This method is used to load user by user name
   * 
   * @param username
   * @return UserDetails object
   * @version 1.0
   */
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    AppUser user = userRepository.findByUsernameAndActiveTrueAndDeletedFalse(username);

    Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
    
    for (Role role : user.getRoles()) {
      
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      
    }

    return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
  }
}
