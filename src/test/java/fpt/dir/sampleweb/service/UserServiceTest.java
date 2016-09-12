package fpt.dir.sampleweb.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import fpt.dir.sampleweb.model.AppUser;
import fpt.dir.sampleweb.repository.UserRepository;
import fpt.dir.sampleweb.service.UserServiceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

  @Mock
  UserRepository userResponsitory;
  
  @InjectMocks
  UserServiceImpl userService;

  private List<AppUser> users;
  private AppUser user;

  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);

    users = new ArrayList<AppUser>();
    user = new AppUser();
    
    user.setId(1L);
    user.setUsername("admin");
    user.setPassword("12345678");
    user.setEmail("admin@fsoft.com");
    user.setActive(true);

    users.add(user);

    when(userResponsitory.save(user)).thenReturn(user);
    when(userResponsitory.findByUsernameAndActiveTrue("admin")).thenReturn(user);
    
  }

  @After
  public void tearDown() throws Exception {
    
  }

  @Test
  public void UT_33_testFindByUsernameExist() {

    AppUser newUser;

    newUser = userService.findByUsernameAndActiveTrue("admin");

    assertEquals("admin", newUser.getUsername());

  }

  @Test
  public void UT_34_testFindByUsernameNotExist() {

    AppUser newUser;

    when(userService.findByUsernameAndActiveTrue("nothuman")).thenReturn(null);

    newUser = userService.findByUsernameAndActiveTrue("nothuman");

    assertNull(newUser);

  }
  
  @Test
  public void UT_35_testFindByIdExist() {

    AppUser oldUser;
    AppUser newUser;
    
    oldUser = userService.findByUsernameAndActiveTrue("admin");

    when(userResponsitory.findByIdAndActiveTrue(oldUser.getId())).thenReturn(user);

    newUser = userService.findByIdAndActiveTrue(oldUser.getId());

    assertNotNull(newUser);

  }

  @Test
  public void UT_36_testFindByIdNotExist() {

    AppUser newUser;

    when(userResponsitory.findByIdAndActiveTrue(0)).thenReturn(null);

    newUser = userService.findByIdAndActiveTrue(0);

    assertNull(newUser);

  }
  
  @Test
  public void UT_37_testFindAll() {

    when(userResponsitory.findAll()).thenReturn(users);

    users = userService.findAll();

    assertNotNull(users);

  }
  
  @Test
  public void UT_38_testDeleteById() {

    AppUser checkUser;
    AppUser check;

    when(userResponsitory.findById(1L)).thenReturn(user);

    checkUser = userService.findByUsernameAndActiveTrue("admin");

    check = userService.delete(checkUser.getId());

    assertNull(check);

  }
  
  @Test
  public void UT_39_testDelete() {

    AppUser checkUser;
    AppUser check;

    checkUser = userService.findByUsernameAndActiveTrue("admin");

    check = userService.delete(checkUser);

    assertNull(check);

  }
}
