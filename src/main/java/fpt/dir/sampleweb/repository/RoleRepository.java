package fpt.dir.sampleweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fpt.dir.sampleweb.model.Role;



/**
*This Repository extents JpaRepository will
*handle all the role database interactions
*
* @author  HoangNTT
* @version 1.0
* @since   2016-08-20
*/
public interface RoleRepository extends JpaRepository<Role, Long> {

  /**
     * This method will get role by id
     * @param id
     * @return Role object
     * @version 1.0
     */
  Role findById(long id);
  
}
