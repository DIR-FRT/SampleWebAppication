package fpt.dir.sampleweb.validator;

import fpt.dir.sampleweb.model.AppUser;
import fpt.dir.sampleweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * This class implements Validator of spring validation <br>
 * Using {@link ValidationUtils} and {@link Errors} for validating the attribute
 * of {@link AppUser} class<br>
 * <br>
 * Include the validation for:<br>
 * - <i>username</i> is empty<br>
 * - <i>username</i>'s size in beetween 6 to 32 characters<br>
 * - <i>username</i> is duplicated<br>
 * - <i>password</i> is empty<br>
 * - <i>password</i> size in between 8 to 32 characters<br>
 * - <i>password</i> confirm is matched<br>
 * - <i>email</i> is empty<br>
 * - <i>email</i> is valid<br>
 *
 * @author HoangNTT
 * @version 1.0
 * @since 2016-08-20
 * @see Errors
 * @see ValidationUtils
 */
@Component
public class UserValidator implements Validator {

  @Autowired
  private UserService userService;

  /**
   * Can this {@link Validator} {@link #validate(Object, Errors) validate}
   * instances of the supplied {@code aClass}?
   * <p></p>
   * This method is <i>typically</i> implemented like so:
   * 
   * <pre class="code">
   * return Foo.class.isAssignableFrom(aClass);
   * </pre>
   * <p></p>
   * (Where {@code Foo} is the class (or superclass) of the actual object
   * instance that is to be {@link #validate(Object, Errors) validated}.)
   * 
   * @param aClass
   *            the {@link Class} that this {@link Validator} is being asked
   *            if it can {@link #validate(Object, Errors) validate}
   * @return {@code true} if this {@link Validator} can indeed
   *         {@link #validate(Object, Errors) validate} instances of the
   *         supplied {@code aClass}
   */
  @Override
  public boolean supports(Class<?> aClass) {

    return AppUser.class.equals(aClass);

  }

  /**
   * This function call another functions for validating username, password,
   * email
   * 
   * @param target
   *            the object that is to be validated, in here it's
   *            {@link AppUser}
   * @param errors
   *            contextual state about the validation process
   * @version 1.0
   */
  @Override
  public void validate(Object target, Errors errors) {

    AppUser user = (AppUser) target;

    validateUsernameIsDuplicate(user, errors);
    validateUsernameIsEmpty(errors);
    validateUsernameSize(user, errors);
    validateNoUserName(user, errors);

  }

  /**
   * This function call another functions for validating password,
   * email
   * 
   * @param target
   *            the object that is to be validated, in here it's
   *            {@link AppUser}
   * @param errors
   *            contextual state about the validation process
   * @version 1.0
   */
  public void validateNoUserName(AppUser user, Errors errors) {
    
    validatePasswordIsEmpty(errors);
    validatePasswordSize(user, errors);
    validatePasswordConfirmIsMatched(user, errors);
    validateEmailIsEmpty(errors);
    validateEmailIsValid(user, errors);
    validateEmailIsExist(user, errors);
    
  }

  /**
   * This function uses
   * {@link ValidationUtils#rejectIfEmptyOrWhitespace(Errors, String, String)}
   * to reject errors
   * 
   * @param errors
   *            contextual state about the validation process
   * @see ValidationUtils
   * @see Errors
   * @version 1.0
   */
  public void validateEmailIsEmpty(Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

  }
  
  /**
   * This function's used to check the email is existed.<br>
   * Reject an {@link Errors errors} if {@link AppUser#getUsername()} is not
   * null
   * 
   * @param errors
   *            contextual state about the validation process
   * @param user
   *            the object that is to be validated, in here it's
   *            {@link AppUser}
   * @see Errors
   */
  public void validateEmailIsExist(AppUser user, Errors errors) {

	  if(user.getId() == null){
		  
		  if (userService.findByEmailAndDeletedFalse(user.getEmail()) != null) {

		      errors.rejectValue("email", "Duplicate.userForm.email");
		      
		  }
		      
	  }
	  else if (userService.findByEmailAndIdNot(user.getEmail(), user.getId()) != null) {

	      errors.rejectValue("email", "Duplicate.userForm.email");

	    }

  }

  /**
   * This function's used to check email valid.<br>
   * Using {@link Pattern#compile(String)} and then using
   * {@link Pattern#matcher(CharSequence)} in {@link Pattern} to check if
   * matching email to regex. <br>
   * Then return result to {@link Matcher matcher} Check the email is not null
   * and the {@link Matcher#matches()} in {@link Matcher} return false if not
   * match email to regex.<br>
   * Reject an {@link Errors}
   * 
   * @param errors
   *            contextual state about the validation process
   * @param user
   *            the object that is to be validated, in here it's
   *            {@link AppUser}
   * @see Errors
   * @see Pattern
   * @see Matcher
   */
  public void validateEmailIsValid(AppUser user, Errors errors) {

    Pattern pattern;
    Matcher matcher;

    pattern = Pattern.compile(
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    matcher = pattern.matcher(user.getEmail());

    if (!"".equals(user.getEmail()) && !matcher.matches()) {

      errors.rejectValue("email", "Match.userForm.email");

    }

  }

  /**
   * This function's used to check the passwordConfirm is matched.<br>
   * Reject an {@link Errors} if {@link AppUser#getPasswordConfirm()} equals
   * to {@link AppUser#getPassword()}
   * 
   * @param errors
   *            contextual state about the validation process
   * @param user
   *            the object that is to be validated, in here it's
   *            {@link AppUser}
   * @see Errors
   */
  public void validatePasswordConfirmIsMatched(AppUser user, Errors errors) {

    if (!user.getPasswordConfirm().equals(user.getPassword())) {

      errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");

    }

  }

  /**
   * This function's used to check the passwordSize is in between 8 to 32
   * characters.<br>
   * Reject an {@link Errors errors} if length of
   * {@link AppUser#getPassword()} is in between 8 to 32 characters
   * 
   * @param errors
   *            contextual state about the validation process
   * @param user
   *            the object that is to be validated, in here it's
   *            {@link AppUser}
   * @see Errors
   */
  public void validatePasswordSize(AppUser user, Errors errors) {

    if (user.getPassword().length() != 0 
        && (user.getPassword().length() < 8 || user.getPassword().length() > 32)) {

      errors.rejectValue("password", "Size.userForm.password");

    }

  }

  /**
   * This function uses
   * {@link ValidationUtils#rejectIfEmptyOrWhitespace(Errors, String, String)}
   * to reject errors
   * 
   * @param errors
   *            contextual state about the validation process
   * @see ValidationUtils
   * @see Errors
   * @version 1.0
   */
  public void validatePasswordIsEmpty(Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

  }

  /**
   * This function's used to check the usernameSize is in between 8 to 32 characters.<br>
   * Reject an {@link Errors errors} if length of 
   * {@link AppUser#getUsername()} is in between 6 to 32 characters
   * 
   * @param errors contextual state about the validation process
   * @param user the object that is to be validated, in here it's {@link AppUser}
   * @see Errors
   */
  public void validateUsernameSize(AppUser user, Errors errors) {
    
    if (user.getUsername().length() != 0
        && (user.getUsername().length() < 6 || user.getUsername().length() > 32)) {

      errors.rejectValue("username", "Size.userForm.username");

    }
    
  }

  /**
   * This function's used to check the username is existed.<br>
   * Reject an {@link Errors errors} if {@link AppUser#getUsername()} is not
   * null
   * 
   * @param errors
   *            contextual state about the validation process
   * @param user
   *            the object that is to be validated, in here it's
   *            {@link AppUser}
   * @see Errors
   */
  public void validateUsernameIsDuplicate(AppUser user, Errors errors) {

    if (userService.findByUsernameAndDeletedFalse(user.getUsername()) != null) {

      errors.rejectValue("username", "Duplicate.userForm.username");

    }

  }

  /**
   * This function uses
   * {@link ValidationUtils#rejectIfEmptyOrWhitespace(Errors, String, String)}
   * to reject errors
   * 
   * @param errors
   *            contextual state about the validation process
   * @see ValidationUtils
   * @see Errors
   * @version 1.0
   */
  public void validateUsernameIsEmpty(Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

  }

}
