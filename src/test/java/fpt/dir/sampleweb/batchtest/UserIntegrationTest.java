package fpt.dir.sampleweb.batchtest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import fpt.dir.sampleweb.model.AppUser;
import fpt.dir.sampleweb.service.UserService;

/**
 * ControllerTest.java
 *
 * Version 1.0
 *
 * Date: Sep 25, 2016
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * -----------------------------------------------------------------------
 * 27/09/2016 BinhMTP Create
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:appconfig-data.xml", "classpath:appconfig-mvc.xml",
		"classpath:appconfig-security.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserIntegrationTest {
	private MockMvc mockMvc;
	private RequestBuilder requestBuilder = null;

	@Autowired
	private WebApplicationContext awc;
	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(this.awc).build();
	}

	@Test
	public void IT_865_Login_View() throws Exception {
		requestBuilder = MockMvcRequestBuilders.get("/login");
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("login"));
	}

	@Test
	public void IT_866_Create_User() throws Exception {
		requestBuilder = MockMvcRequestBuilders.get("/login");
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("login"));

		requestBuilder = MockMvcRequestBuilders.get("/registration");
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("registration"));

		requestBuilder = MockMvcRequestBuilders.post("/registration").param("username", "testing")
				.param("password", "12345678").param("passwordConfirm", "12345678").param("email", "test@email.com")
				.param("active", "true");
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
	}

	@Test
	public void IT_867_Edit_User() throws Exception {
		requestBuilder = MockMvcRequestBuilders.get("/userList");
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("userList"));

		AppUser user = userService.findByUsernameAndActiveTrue("testing");

		requestBuilder = MockMvcRequestBuilders.get("/editUser").param("id", user.getId().toString());
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("editUser"));

		requestBuilder = MockMvcRequestBuilders.post("/editUser").param("id", user.getId().toString())
				.param("password", "123456789").param("passwordConfirm", "123456789")
				.param("email", "test_email@email.com");
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.redirectedUrl("/userList"));
	}

	@Test
	public void IT_868_Delete_User() throws Exception {
		requestBuilder = MockMvcRequestBuilders.get("/userList");
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("userList"));

		AppUser user = userService.findByUsernameAndActiveTrue("testing");

		requestBuilder = MockMvcRequestBuilders.get("/deleteUser").param("id", user.getId().toString());
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.redirectedUrl("/userList"));
	}
}
