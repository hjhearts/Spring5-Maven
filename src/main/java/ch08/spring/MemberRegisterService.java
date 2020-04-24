package ch08.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class MemberRegisterService {
    @Autowired
    private MemberDAO memberDAO;

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
        memberDAO.insert(newMember);
        return newMember.getId();
    }
}
