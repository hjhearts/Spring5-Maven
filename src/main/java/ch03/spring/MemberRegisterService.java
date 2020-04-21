package ch03.spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
    private MemberDAO memberDAO;
    public MemberRegisterService(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }

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
