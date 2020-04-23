package ch08.spring;

public class MemberInfoPrinter {
    private MemberDAO memberDAO;
    private MemberPrinter memberPrinter;
    public void setMemberDAO(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }
    public void setMemberPrinter(MemberPrinter memberPrinter){
        this.memberPrinter = memberPrinter;
    }
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
