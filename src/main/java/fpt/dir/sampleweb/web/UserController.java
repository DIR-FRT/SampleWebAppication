package fpt.dir.sampleweb.web;

import fpt.dir.sampleweb.model.AppUser;
import fpt.dir.sampleweb.service.UserService;
import fpt.dir.sampleweb.validator.UserValidator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This Controller will handle all User request
 *
 * @author HoangNTT
 * @version 1.0
 * @since 2016-08-20
 */
@Controller
public class UserController {
  
  @Autowired
  private UserService userService;

  @Autowired
  private UserValidator userValidator;

  /**
   * This method is used to registration
   * 
   * @param model
   * @return registration
   * @version 1.0
   */
  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public String registration(Model model) {
    
    model.addAttribute("userForm", new AppUser());

    return "registration";
  }

  /**
   * This method is used to registration
   * 
   * @param userForm
   * @param bindingResult
   * @return registration
   * @version 1.0
   */
  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public String registration(@ModelAttribute("userForm") AppUser userForm,
      BindingResult bindingResult) {
    
    userValidator.validate(userForm, bindingResult);

    if (bindingResult.hasErrors()) {
      
      return "registration";
      
    }

    userService.save(userForm);

    // securityService.autologin(userForm.getUsername(),
    // userForm.getPasswordConfirm());

    return "redirect:/";
    
  }

  /**
   * This method is used to login
   * 
   * @param model
   * @param error
   * @param logout
   * @return login
   * @version 1.0
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model, String error, String logout) {

    if (error != null) {
      
      model.addAttribute("error", "ユーザネームおよびパスワードが間違ました。");
      
    }
      
    if (logout != null) {
      
      model.addAttribute("message", "ログアウト成功！");
      
    }

    return "login";
    
  }

  /**
   * This method is used to logout
   * 
   * @return logout
   * @version 1.0
   */
  @RequestMapping("/logout")
  public String showLoggedout() {
    
    return "logout";
    
  }

  /**
   * This method is used to show list user
   * 
   * @param model
   * @return userList
   * @version 1.0
   */
  @RequestMapping(value = { "/" }, method = RequestMethod.GET)
  public String userList(Model model) {

    List<AppUser> users = userService.findByDeletedFalse();

    model.addAttribute("userList", users);

    return "userList";

  }

  /**
   * This method is used to redirect to edit user
   * 
   * @param id id of App_User from View
   * @param model object return to form view
   * @return editUser
   * @version 1.0
   */
  @RequestMapping(value = "/editUser", method = RequestMethod.GET)
  public String edit(@RequestParam long id, Model model) {

    AppUser user = userService.findById(id);

    model.addAttribute("userForm", user);

    return "editUser";
  }

  /**
   * This method is used to edit user
   * 
   * @param userForm get data from view to App_User object
   * @param bindingResult object for rejecting errors
   * @return redirect:/userList
   * @version 1.0
   */
  @RequestMapping(value = "/editUser", method = RequestMethod.POST)
  public String edit(@ModelAttribute("userForm") AppUser userForm, BindingResult bindingResult) {

    AppUser user = userService.findById(userForm.getId());
    
    userForm.setUsername(user.getUsername());
    
    userValidator.validateNoUserName(userForm, bindingResult);

    if (bindingResult.hasErrors()) {
      
      return "editUser";
      
    }      

    userService.save(userForm);

    return "redirect:/";
    
  }

  /**
   * This method is used to disable user
   * 
   * @return redirect:/userList
   * @version 1.0
   */
  @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
  public String deleteUser(@RequestParam long id, Model model) {

    userService.delete(id);

    return "redirect:/";

  }
  
  /**
   * This method is used to disable user
   * 
   * @return redirect:/userList
   * @version 1.0
   */
  @RequestMapping(value = "/disableUser", method = RequestMethod.GET)
  public String disableUser(@RequestParam long id, Model model) {

    userService.disable(id);

    return "redirect:/";

  }
  
  /**
   * This method is used to enable user
   * 
   * @return redirect:/userList
   * @version 1.0
   */
  @RequestMapping(value = "/enableUser", method = RequestMethod.GET)
  public String enableUser(@RequestParam long id, Model model) {

    userService.enable(id);

    return "redirect:/";

  }

}
