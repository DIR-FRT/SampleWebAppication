package fpt.dir.sampleweb.service;

import java.util.List;

import fpt.dir.sampleweb.model.AppUser;

/**
* This service will handle all the user interactions
*
* @author  HoangNTT
* @version 1.0
* @since   2016-08-20
*/
public interface UserService {
  
  /**
  * This method is used to save user
  * 
  * @param user AppUser object
  * @return AppUser object
  * @version 1.0
  */
  AppUser save(AppUser user);

  /**
  * This method is used to get AppUser by user name
  * 
  * @param username
  * @return AppUser object
  * @version 1.0
  */
  AppUser findByUsername(String username);

  /**
  * This method is used to get active AppUser by user name
  * 
  * @param username
  * @return AppUser object
  * @version 1.0
  */
  AppUser findByUsernameAndActiveTrue(String username);
  
  /**
  * This method is used to get all AppUser
  * 
  * @return List AppUser List
  * @version 1.0
  */
  List<AppUser> findAll();

  /**
  * This method is used to get AppUser by id
  * 
  * @param id
  * @return AppUser object
  * @version 1.0
  */
  AppUser findById(long id);

  /**
  * This method is used to get active AppUser by id
  * 
  * @param id
  * @return AppUser object
  * @version 1.0
  */
  AppUser findByIdAndActiveTrue(long id);

  /**
  * This method is used to delete user by id
  * 
  * @param id
  * @return AppUser object
  * @version 1.0
  */
  AppUser delete(long id);
  
  /**
  * This method is used to delete user
  * 
  * @param id
  * @return AppUser object
  * @version 1.0
  */
  AppUser delete(AppUser user);
  
}
