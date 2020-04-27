package ch08.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberDeleteService {
    @Autowired
    private MemberDAO memberDAO;

    @Transactional
    public void deleteMember(String email, String pass){
        Member member = memberDAO.selectByEmail(email);
        if(member == null){
            throw new MemberNotFoundException();
        }else{
            if(!member.getPassword().equals(pass)){
                throw new WrongPasswordException();
            }
            memberDAO.deleteMember(member);
        }
    }
}
