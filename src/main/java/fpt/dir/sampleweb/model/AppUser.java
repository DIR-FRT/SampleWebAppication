package fpt.dir.sampleweb.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This class represents <i>User</i> object
 *
 * @author HoangNTT
 * @version 1.0
 * @since 2016-08-20
 */
@Entity
@Table(name = "app_user")
public class AppUser {

  private Long id;
  private String username;
  private String password;
  private String passwordConfirm;
  private String email;
  private Boolean active = true;
  private Boolean deleted = false;
  private Set<Role> roles;

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
  * This method is used to get user name
  * 
  * @return String This returns user name
  * @version 1.0
  */
  public String getUsername() {
    return username;
  }

  /**
  * This method is used to set user name
  * 
  * @version 1.0
  */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
  * This method is used to get password
  * 
  * @return String This returns password
  * @version 1.0
  */
  public String getPassword() {
    return password;
  }

  /**
  * This method is used to set password
  * 
  * @version 1.0
  */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
  * This method is used to get passwordConfirm
  * 
  * @return String This returns passwordConfirm
  * @version 1.0
  */
  @Transient
  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  /**
   * This method is used to set passwordConfirm
   * 
   * @version 1.0
   */
  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }

  /**
   * This method is used to get roles
   * 
   * @return String This returns roles
   * @version 1.0
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  public Set<Role> getRoles() {
    return roles;
  }

  /**
   * This method is used to set roles
   * 
   * @version 1.0
   */
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  /**
   * This method is used to get email
   * 
   * @return String This returns email
   * @version 1.0
   */
  public String getEmail() {
    return email;
  }

  /**
   * This method is used to set email
   * 
   * @version 1.0
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * This method is used to get active
   * 
   * @return Boolean This returns active
   * @version 1.0
   */
  public Boolean getActive() {
    return active;
  }

  /**
   * This method is used to set active
   * 
   * @version 1.0
   */
  public void setActive(Boolean active) {
    this.active = active;
  }
  
  /**
   * This method is used to get deleted
   * 
   * @return Boolean This returns deleted
   * @version 1.0
   */
  public Boolean getDeleted() {
    return deleted;
  }

  /**
   * This method is used to set deleted
   * 
   * @version 1.0
   */
  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

}
