package ch08.spring;

public class ChangePasswordService {
    private MemberDAO memberDAO;

    public void setMemberDAO(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }

    public void changePassword(String email, String oldPassword, String newPassword){
        Member member = memberDAO.selectByEmail(email);
        if(member == null){
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPassword, newPassword);
        memberDAO.update(member);
    }
}
