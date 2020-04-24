package ch08.main;

import ch08.config.AppCtx;
import ch08.spring.Member;
import ch08.spring.MemberDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainForMemberDAO {
    private static MemberDAO memberDAO;
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        memberDAO = ctx.getBean("memberDAO", MemberDAO.class);
        selectAll();
        insertMember();
    }

    private static void selectAll(){
        System.out.println("-----SELECT ALL-----");
        int total = memberDAO.count();
        System.out.println("전체 데이터 : " + total);
        List<Member> members = memberDAO.selectAll();
        for(Member m : members){
            System.out.println(m.getId() + ":" + m.getEmail() + ", " + m.getName());
        }
    }

    private static void updateMember(){
        System.out.println("-----UPDATE MEMBER-----");
        Member member = memberDAO.selectByEmail("covid@covid.net");
        String oldPwd = member.getPassword();
        String newPwd = Double.toHexString(Math.random());
        member.changePassword(oldPwd, newPwd);
        memberDAO.update(member);
        System.out.println("암호 변경 " + oldPwd + ">>" + newPwd);
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

    private static void insertMember(){
        System.out.println("-----INSERT MEMBER-----");
        String prefix = formatter.format(LocalDateTime.now());
        Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());
        memberDAO.insert(member);
        System.out.println("회원추가 완료 : " + member.getId());
    }

}
