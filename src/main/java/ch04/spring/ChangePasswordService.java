package ch04.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

    @Autowired
    private MemberDAO memberDAO;

    public void changePassword(String email, String oldPassword, String newPassword){
        Member member = memberDAO.selectByEmail(email);
        if(member == null){
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPassword, newPassword);
        memberDAO.update(member);
    }
}
