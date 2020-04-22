package ch05.main;

import ch05.config.AppCtx;
import ch05.spring.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring {
    private static ApplicationContext ctx = null;
    public static void main(String[] args) throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("명령어를 입력하세요.");
            String command = reader.readLine();
            if(command.equalsIgnoreCase("exit")){
                System.out.println("종료");
                break;
            }
            if(command.startsWith("new ")){
                processNewCommand(command.split(" "));
                continue;
            }else if(command.startsWith("change ")){
                processChangeCommand(command.split(" "));
                continue;
            }else if(command.equalsIgnoreCase("list")){
                processListCommand();
                continue;
            }else if(command.startsWith("info ")){
                processInfoCommand(command.split(" "));
                continue;
            }else if(command.equalsIgnoreCase("version")){
                processVersionCommand();
                continue;
            }
            printHelp();
        }
    }
    private static void processNewCommand(String[] args){
        if(args.length != 5){
            printHelp();
            return;
        }
        MemberRegisterService registerService = ctx.getBean(MemberRegisterService.class);
        RegisterRequest request = new RegisterRequest();
        request.setEmail(args[1]);
        request.setName(args[2]);
        request.setPassword(args[3]);
        request.setConfirmPassword(args[4]);

        if(!request.isPasswordEqualsToConfirmPassword()){
            System.out.println("암호화 확인이 일치하지 않습니다.");
            return;
        }
        try{
            registerService.regist(request);
            System.out.println("등록 완료");
        }catch(DuplicateMemberException e){
            System.out.println("이미 존재하는 이메일 입니다.");
        }
    }

    private static void processChangeCommand(String[] args){
        if(args.length != 4){
            printHelp();
            return;
        }
        ChangePasswordService changePasswordService = ctx.getBean(ChangePasswordService.class);
        try{
            changePasswordService.changePassword(args[1], args[2], args[3]);
            System.out.println("암호를 변경했습니다.");
        }catch(MemberNotFoundException e) {
            System.out.println("존재하지 않는 아이디입니다.");
        }catch(WrongPasswordException e){
            System.out.println("패스워드가 일치하지 않습니다.");
        }
    }

    private static void printHelp(){
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 암호 변경암호");
        System.out.println("info 이메일");
        System.out.println();
    }

    private static void processListCommand(){
        MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }

    private static void processInfoCommand(String[] args){
        if(args.length != 2){
            printHelp();
            return;
        }
        MemberInfoPrinter memberInfoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        memberInfoPrinter.printMemberInfo(args[1]);
    }
    private static void processVersionCommand(){
        VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }
}
