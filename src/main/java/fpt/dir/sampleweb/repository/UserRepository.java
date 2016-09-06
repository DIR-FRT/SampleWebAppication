package fpt.dir.sampleweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fpt.dir.sampleweb.model.AppUser;

import java.util.List;




/**
*This Repository extents JpaRepository will
*handle all the user database interactions
*
* @author  HoangNTT
* @version 1.0
* @since   2016-08-20
*/
public interface UserRepository extends JpaRepository<AppUser, Long> {
    
  /**
  * This method will get user by id
  * 
  * @param id
  * @return AppUser object
  * @version 1.0
  */
  AppUser findById(long id);
  
  /**
  * This method will get all active user
  * 
  * @return List AppUser List 
  * @version 1.0
  */
  List<AppUser> findByActiveTrue();

  /**
  * This method will get active user by name
  * 
  * @param username
  * @return AppUser object
  * @version 1.0
  */
  AppUser findByUsernameAndActiveTrue(String username);
  
  /**
  * This method will get active user by id
  * 
  * @param id
  * @return AppUser object
  * @version 1.0
  */
  AppUser findByIdAndActiveTrue(long id);

  /**
  * This method will get user by name
  * @param username
  * @return AppUser object
  * @version 1.0
  */
  AppUser findByUsername(String username);

}
