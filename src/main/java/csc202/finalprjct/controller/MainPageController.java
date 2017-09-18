package csc202.finalprjct.controller;

import java.util.Map;

import csc202.finalprjct.EnumEditor;
import csc202.finalprjct.entity.Gender;
import csc202.finalprjct.entity.User;
import csc202.finalprjct.entity.UserService;
import csc202.finalprjct.security.service.SecurityService;
import csc202.finalprjct.security.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainPageController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Gender.class, new EnumEditor(Gender.class));
        /* Converts empty strings into null when a form is submitted */
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    // inject via application.properties
    @Value("${main.message:test}")
    private String message = "Hello World";

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("userForm", new User());
        return "main";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }
//-----------------------------------------------------------------------


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

//    @PostMapping("/greeting")
//    public String greetingSubmit(@ModelAttribute Greeting greeting) {
//        return "result";
//    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
//
//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

//------------------------------------------------------------------------------

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "signin";
    }
}