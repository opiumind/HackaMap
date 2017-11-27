package csc202.finalprjct.security.service;

import csc202.finalprjct.entity.User;
import csc202.finalprjct.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //todo check for the symbol `:`

//        Firstname
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
//        if (user.getFirstName().length() < 1) {
//            errors.rejectValue("firstName", "Size.userForm.firstName");
//        }

//        Lastname
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
//        if (user.getLastName().length() < 1) {
//            errors.rejectValue("lastName", "Size.userForm.lastName");
//        }

//        Date of birth
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "NotEmpty");
//        Gender
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty");
//        Email
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "house", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "NotEmpty");
//        if (user.getLastName().length() < 1) {
//            errors.rejectValue("email", "Size.userForm.email");
//        }

//        Username
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user != null) {
            if (user.getUsername() != null && (user.getUsername().length() < 6 || user.getUsername().length() > 32)) {
                errors.rejectValue("username", "Size.userForm.username");
            }
            if (userService.findByUsername(user.getUsername()) != null) {
                errors.rejectValue("username", "Duplicate.userForm.username");
            }
        }

//        Password (at least 1 number, 1 upper case letter, 1 lower case letter, 1 special character)
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user != null) {
            if (user.getPassword() != null
                    && !user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")) {
                errors.rejectValue("password", "Rules.userForm.password");
            }
        }

//        Confirm password
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty");
        if (user != null) {
            if (user.getConfirmPassword() != null && !user.getConfirmPassword().equals(user.getPassword())) {
                errors.rejectValue("confirmPassword", "Diff.userForm.confirmPassword");
            }
        }
    }
}
