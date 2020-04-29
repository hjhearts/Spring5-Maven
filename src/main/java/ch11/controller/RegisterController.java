package ch11.controller;

import ch11.spring.DuplicateMemberException;
import ch11.spring.MemberRegisterService;
import ch11.spring.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private MemberRegisterService memberRegisterService;

    @RequestMapping(value = "/register/step1")
    public String handleStep1(){
        return "register/step1";
    }

    @PostMapping(value = "/register/step2")
    public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") boolean agreement,
                              RegisterRequest registerRequest){
        if(!agreement){
            return "register/step1";
        }
        return "register/step2";
    }

    @GetMapping(value = "/register/step2")
    public String handleStep2Get(){
        return "redirect:/register/step1";
    }

    @RequestMapping(value = "/register/step3", method = RequestMethod.POST)
    public String handleStep3(@Valid @ModelAttribute("registerRequest") RegisterRequest regRequest, Errors errors){
        if(errors.hasErrors()){
            return "register/step2";
        }
        if(regRequest.isPasswordEqualsToConfirmPassword()) {
            try {
                memberRegisterService.regist(regRequest);
                return "register/step3";
            } catch (DuplicateMemberException e) {
                errors.rejectValue("email", "duplicate");
                return "register/step2";
            }
        }else{
            errors.rejectValue("confirmPassword", "nomatch");
            return "register/step2";
        }
    }
/*
    @InitBinder
    protected  void initBinder(WebDataBinder binder){
        binder.setValidator(new RegisterRequestValidator());
    }

 */

}
