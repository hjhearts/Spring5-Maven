package ch11.controller;

import ch11.spring.Member;
import ch11.spring.MemberDAO;
import ch11.spring.MemberNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberDetailController {
    @Autowired
    private MemberDAO memberDAO;

    @RequestMapping(value = "/members/{id}")
    public String detail(@PathVariable("id") Long memId, Model model){
        Member member = memberDAO.selectById(memId);
        if(member == null){
            throw new MemberNotFoundException();
        }
        model.addAttribute("member", member);
        return "member/memberDetail";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(){
        return "member/invalidId";
    }
/*
    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(){
        return "member/noMember";
    }

 */
}
