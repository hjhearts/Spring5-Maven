package ch11.controller;


import ch11.spring.AuthInfo;
import ch11.spring.AuthService;
import ch11.spring.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public String form(LoginCommand loginCommand, @CookieValue(value = "REMEMBER", required = false)Cookie cookie){
        if(cookie != null){
            loginCommand.setEmail(cookie.getValue());
            loginCommand.setRememberEmail(true);
        }
        return "login/loginForm";
    }

    @PostMapping
    public String submit(@ModelAttribute("loginCommand") LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response){
        new LoginCommandValidator().validate(loginCommand, errors);
        if(errors.hasErrors()){
            return "login/loginForm";
        }
        try{
            AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
            session.setAttribute("authInfo", authInfo);
            Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
            rememberCookie.setPath("/");
            if(loginCommand.isRememberEmail()){
                rememberCookie.setMaxAge(60 * 60 * 24 * 30);
            }else{
                rememberCookie.setMaxAge(0);
            }
            response.addCookie(rememberCookie);
            return "login/loginSuccess";
        }catch(WrongPasswordException e){
            errors.reject("idPasswordNotMatching");
            return "login/loginForm";
        }
    }
}
