package ch11.controller;

import ch11.spring.AuthInfo;
import ch11.spring.ChangePasswordService;
import ch11.spring.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/edit/changePassword")
public class ChangePwdController {
    @Autowired
    private ChangePasswordService changePasswordService;

    @GetMapping
    public String form(@ModelAttribute("command") ChangePwdCommand pwdCommand){
        return "edit/changePwdForm";
    }

    @PostMapping
    public String submit(@ModelAttribute("command") ChangePwdCommand pwdCommand, Errors errors, HttpSession session){
        new ChangePwdCommandValidator().validate(pwdCommand,  errors);
        if(errors.hasErrors()){
            return "edit/changePwdForm";
        }
        AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
        try{
            changePasswordService.changePassword(authInfo.getEmail(),
                    pwdCommand.getCurrentPassword(),
                    pwdCommand.getNewPassword());
            return "edit/changedPwd";
        }catch(WrongPasswordException ex){
            errors.rejectValue("currentPassword", "notMatching");
            return "edit/changePwdForm";
        }
    }
}
