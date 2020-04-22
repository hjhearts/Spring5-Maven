package ch04.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    @Qualifier("printer")
    private MemberPrinter memberPrinter;

    public void printMemberInfo(String email){
        Member member = memberDAO.selectByEmail(email);
        if(member == null){
            System.out.println("데이터 없음");
            return;
        }
        memberPrinter.print(member);
        System.out.println();
    }
}
