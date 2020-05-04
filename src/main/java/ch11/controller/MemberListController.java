package ch11.controller;

import ch11.spring.Member;
import ch11.spring.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MemberListController {
    @Autowired
    private MemberDAO memberDAO;

    @RequestMapping(value = "/members")
    public String list(@ModelAttribute("cmd") ListCommand listCommand, Errors errors, Model model){
        if(errors.hasErrors()){
            return "member/memberList";
        }
        if(listCommand.getFrom() != null && listCommand.getTo() != null){
            List<Member> members = memberDAO.selectByRegDate(listCommand.getFrom(), listCommand.getTo());
            model.addAttribute("members", members);
        }
        return "member/memberList";
    }
}
