package ch11.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private MemberDAO memberDAO;

    public AuthInfo authenticate(String email, String password){
        Member member = memberDAO.selectByEmail(email);
        if(member == null){
            throw new WrongPasswordException();
        }
        if(!member.matchPassword(password)){
            throw new WrongPasswordException();
        }
        return new AuthInfo(member.getId(), member.getEmail(), member.getName());
    }
}
