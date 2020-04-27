package ch11.controller;

import ch11.spring.DuplicateMemberException;
import ch11.spring.MemberRegisterService;
import ch11.spring.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
                              Model model){
        if(!agreement){
            return "register/step1";
        }
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register/step2";
    }

    @GetMapping(value = "/register/step2")
    public String handleStep2Get(){
        return "redirect:/register/step1";
    }

    @RequestMapping(value = "/register/step3", method = RequestMethod.POST)
    public String handleStep3(@ModelAttribute("registerRequest") RegisterRequest regRequest){
        if(regRequest.isPasswordEqualsToConfirmPassword()) {
            try {
                memberRegisterService.regist(regRequest);
                return "register/step3";
            } catch (DuplicateMemberException e) {
                regRequest.setNotice("duplicated");
                return "register/step2";
            }
        }else{
            regRequest.setNotice("checkPassword");
            return "register/step2";
        }
    }


}
