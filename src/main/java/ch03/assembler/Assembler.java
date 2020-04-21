package ch03.assembler;

import ch03.spring.ChangePasswordService;
import ch03.spring.MemberDAO;
import ch03.spring.MemberRegisterService;

public class Assembler {
    private MemberDAO memberDAO;
    private MemberRegisterService regService;
    private ChangePasswordService chgPwdService;

    public Assembler(){
        memberDAO = new MemberDAO();
        regService = new MemberRegisterService(memberDAO);
        chgPwdService = new ChangePasswordService();
        chgPwdService.setMemberDAO(memberDAO);
    }

    public MemberDAO getMemberDAO(){
        return memberDAO;
    }

    public MemberRegisterService getMemberRegisterService(){
        return regService;
    }

    public ChangePasswordService getChangePasswordService(){
        return chgPwdService;
    }
}
