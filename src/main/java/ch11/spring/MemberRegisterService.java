package ch11.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MemberRegisterService {
    @Autowired
    private MemberDAO memberDAO;

    @Transactional
    public Long regist(RegisterRequest registerRequest){
        Member member = memberDAO.selectByEmail(registerRequest.getEmail());
        if(member != null){
            throw new DuplicateMemberException("duplicated : " + registerRequest.getEmail());
        }
        Member newMember = new Member(
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getName(),
                LocalDateTime.now());
        if(newMember.getPassword().equals(registerRequest.getConfirmPassword())) {
            memberDAO.insert(newMember);
        }else{
            throw new WrongPasswordException();
        }
        return newMember.getId();
    }
}
