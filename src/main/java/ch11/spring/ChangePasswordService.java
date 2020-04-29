package ch11.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangePasswordService {
    @Autowired
    private MemberDAO memberDAO;

    @Transactional
    public void changePassword(String email, String oldPassword, String newPassword){
        Member member = memberDAO.selectByEmail(email);
        if(member == null){
            throw new MemberNotFoundException();
        }
        if(!member.getPassword().equals(oldPassword)){
            throw new WrongPasswordException();
        }
        member.changePassword(oldPassword, newPassword);
        memberDAO.update(member);
    }
}
