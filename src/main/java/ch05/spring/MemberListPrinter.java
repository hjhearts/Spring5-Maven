package ch05.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("listPrinter")
public class MemberListPrinter {
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MemberPrinter memberPrinter;

    public void printAll(){
        Collection<Member> members = memberDAO.selectAll();
        members.forEach(member ->memberPrinter.print(member));
    }
}
