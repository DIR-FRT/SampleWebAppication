package fpt.dir.sampleweb.service;

import fpt.dir.sampleweb.model.AppUser;
import fpt.dir.sampleweb.model.Role;
import fpt.dir.sampleweb.repository.RoleRepository;
import fpt.dir.sampleweb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* This class implements UserSerive
* 
* @author  HoangNTT
* @version 1.0
* @since   2016-08-20
*/

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public AppUser save(AppUser user) {

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    Set<Role> roles = new HashSet<Role>();

    roles.add(roleRepository.findById(1));

    user.setRoles(roles);

    return userRepository.save(user);
  }

  @Override
  public AppUser findByUsernameAndActiveTrue(String username) {
    return userRepository.findByUsernameAndActiveTrue(username);
  }

  @Override
  public AppUser findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
  
  @Override
  public AppUser findById(long id) {

    return userRepository.findById(id);

  }
  
  @Override
  public AppUser findByIdAndActiveTrue(long id) {

    return userRepository.findByIdAndActiveTrue(id);

  }

  @Override
  public List<AppUser> findAll() {

    List<AppUser> users = userRepository.findAllByOrderByIdAsc();
    
    return users;

  }
  
  @Override
  public AppUser delete(long id) {

    AppUser user = new AppUser();

    user = userRepository.findById(id);

    user.setActive(false);

    userRepository.save(user);

    return userRepository.findByIdAndActiveTrue(user.getId());

  }

  @Override
  public AppUser delete(AppUser user) {

    user.setActive(false);

    userRepository.save(user);

    return userRepository.findByIdAndActiveTrue(user.getId());
  }

  @Override
  public AppUser disable(long id) {
	  
	  AppUser user = new AppUser();
	  
	  user = userRepository.findById(id);
	  user.setActive(false);
	  userRepository.save(user);

	  return userRepository.findByIdAndActiveTrue(user.getId());
  }

  @Override
  public AppUser enable(long id) {

	  AppUser user = new AppUser();
	  
	  user = userRepository.findById(id);
	  user.setActive(true);
	  userRepository.save(user);

	  return userRepository.findByIdAndActiveFalse(user.getId());
	  
  }

  @Override
  public AppUser findByEmailAndIdNot(String email, long id) {

	  return userRepository.findByEmailAndIdNot(email, id);
	  
  }

  @Override
  public AppUser findByEmail(String email) {
	  return userRepository.findByEmail(email);
  }

}
