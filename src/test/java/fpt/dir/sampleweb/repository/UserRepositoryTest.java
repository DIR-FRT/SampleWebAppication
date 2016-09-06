package fpt.dir.sampleweb.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import fpt.dir.sampleweb.model.AppUser;
import fpt.dir.sampleweb.repository.UserRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;



import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appconfig-data.xml")
@Transactional
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  private AppUser user;
  private List<AppUser> users;

  @Before
  public void setUp() throws Exception {

    user = new AppUser();
    users = new ArrayList<AppUser>();

    user.setUsername("admin");
    user.setEmail("admin@fdn.vn");
    user.setPassword("12345678");

  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void UT_23_testSave() {

    AppUser checkUser;

    checkUser = userRepository.findByUsernameAndActiveTrue("admin");

    if (checkUser != null) {

      userRepository.delete(checkUser.getId());

    }

    AppUser newUser;

    newUser = userRepository.save(user);

    assertNotNull(newUser);

  }

  @Test
  public void UT_18_testFindByIdExist() {

    AppUser oldUser;

    oldUser = userRepository.findByUsernameAndActiveTrue("admin");

    AppUser newUser;

    newUser = userRepository.findByIdAndActiveTrue(oldUser.getId());

    assertNotNull(newUser);

  }

  @Test
  public void UT_19_testFindByIdNotExist() {

    AppUser newUser;

    newUser = userRepository.findByIdAndActiveTrue(0);

    assertNull(newUser);

  }

  @Test
  public void UT_8_testFindByUsernameExist() {

    AppUser newUser;

    newUser = userRepository.findByUsernameAndActiveTrue("admin");

    assertEquals("admin", newUser.getUsername());

  }

  @Test
  public void UT_17_testFindByUsernameNotExist() {

    AppUser newUser;

    newUser = userRepository.findByUsernameAndActiveTrue("nothuman");

    assertNull(newUser);

  }

  @Test
  public void UT_22_testDelete() {

    AppUser checkUser;

    checkUser = userRepository.findByUsernameAndActiveTrue("admin");

    if (checkUser == null) {

      checkUser = userRepository.save(user);
      

    }

    userRepository.delete(checkUser);
    
    assertNull(userRepository.findByIdAndActiveTrue(checkUser.getId()));

  }
  
  @Test  
  public void UT_21_testDeleteById() {

    AppUser checkUser;

    checkUser = userRepository.findByUsernameAndActiveTrue("admin");

    if (checkUser == null) {

      checkUser = userRepository.save(user);
      

    }

    userRepository.delete(checkUser.getId());
    
    assertNull(userRepository.findByIdAndActiveTrue(checkUser.getId()));

  }
  
  @Test
  public void UT_20_testFindAll() {

    users = userRepository.findAll();
    
    if (users == null){
      
      userRepository.save(user);      
      
    }
      
    users = userRepository.findAll();

    assertNotNull(users);

  }

}
