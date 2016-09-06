package fpt.dir.sampleweb.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
* This class represents <i>Role</i> object
*
* @author  HoangNTT
* @version 1.0
* @since   2016-08-20
*/
@Entity
@Table(name = "role")
public class Role {
  private Long id;
  private String name;
  private Set<AppUser> users;
 
  /**
   * This method is used to get id
   * 
   * @return Long This returns id
   * @version 1.0
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  /**
   * This method is used to set id
   * 
   * @version 1.0
   */
  public void setId(Long id) {
    this.id = id;
  }
  
  /**
   * This method is used to get name
   * 
   * @return String This returns name
   * @version 1.0
   */
  public String getName() {
    return name;
  }

  /**
   * This method is used to set name
   * 
   * @version 1.0
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * This method is used to get users
   * 
   * @return Set This returns users
   * @version 1.0
   */
  @ManyToMany(mappedBy = "roles")
  public Set<AppUser> getUsers() {
    return users;
  }
  

  /**
   * This method is used to set users
   * 
   * @version 1.0
   */
  public void setUsers(Set<AppUser> users) {
    this.users = users;
  }
}
