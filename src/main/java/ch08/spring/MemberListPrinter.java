package ch08.spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

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
