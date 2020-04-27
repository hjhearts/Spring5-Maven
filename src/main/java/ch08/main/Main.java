package ch08.main;

import ch08.config.AppCtx;
import ch08.spring.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static AbstractApplicationContext ctx = null;
    public static void main(String[] args) throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("명령어를 입력하세요.");
            String command = reader.readLine();
            if(command.startsWith("new ")){
                processNewCommand(command.split(" "));
            }else if(command.equalsIgnoreCase("exit")){
                System.out.println("프로그램 종료");
                return;
            }else if(command.startsWith("change ")){
                processChangePassword(command.split(" "));
            }else if(command.startsWith("info ")){
                processInfoCommand(command.split(" "));
            }else if(command.equalsIgnoreCase("list")){
                processListCommand();
            }else if(command.startsWith("delete ")){
                processDeleteCommand(command.split(" "));
            }else{
                helpCommand();
            }
        }
    }

    public static void processNewCommand(String[] args){
        if(args.length != 5){
            System.out.println("인자값을 확인하세요");
            helpCommand();
            return;
        }
        MemberRegisterService registerService = ctx.getBean(MemberRegisterService.class);
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(args[1]);
        registerRequest.setName(args[2]);
        registerRequest.setPassword(args[3]);
        registerRequest.setConfirmPassword(args[4]);
        try {
            if (registerRequest.isPasswordEqualsToConfirmPassword()) {
                long id = registerService.regist(registerRequest);
                System.out.println("회원 추가 완료. ID : " + id);
            } else {
                System.out.println("패스워드를 다시 확인해주세요.");
            }
        }catch(DuplicateMemberException e){
            System.out.println("중복된 EMAIL입니다. >> " + args[1]);
        }
    }

    public static void processChangePassword(String[] args){
        if(args.length != 4){
            System.out.println("인자값을 확인하세요.");
            helpCommand();
            return;
        }
        ChangePasswordService passwordService = ctx.getBean(ChangePasswordService.class);
        try{
            passwordService.changePassword(args[1], args[2], args[3]);
        }catch(MemberNotFoundException e){
            System.out.println("회원 정보가 없습니다.");
        }catch(WrongPasswordException e){
            System.out.println("패스워드가 일치하지 않습니다.");
        }
        System.out.println("비밀번호 변경 완료 :" + args[2] + ">>>" + args[3]);
    }

    public static void processInfoCommand(String[] args){
        if(args.length != 2){
            System.out.println("인자값을 확인하세요.");
            helpCommand();
            return;
        }
        MemberInfoPrinter infoPrinter = ctx.getBean(MemberInfoPrinter.class);
        infoPrinter.printMemberInfo(args[1]);
    }

    public static void processListCommand(){
        MemberListPrinter listPrinter = ctx.getBean(MemberListPrinter.class);
        listPrinter.printAll();
    }

    public static void helpCommand(){
        System.out.println();
        System.out.println("명령어 입력 안내.");
        System.out.println("회원추가 - new email name pass confirmPass");
        System.out.println("비밀번호 변경 - change email old-pass new-pass");
        System.out.println("회원조회 - info email");
        System.out.println("리스트조회 - list");
        System.out.println("회원 삭제 - delete email pass");
        System.out.println();
    }

    public static void processDeleteCommand(String[] args){
        if(args.length != 3){
            System.out.println("인자값을 확인하세요.");
            helpCommand();
            return;
        }
        try {
            MemberDeleteService memberDeleteService = ctx.getBean(MemberDeleteService.class);
            memberDeleteService.deleteMember(args[1], args[2]);
            System.out.println("삭제 완료");
        }catch (MemberNotFoundException e){
            System.out.println("회원이 존재하지 않습니다.");
        }catch (WrongPasswordException e){
            System.out.println("패스워드가 일치하지 않습니다.");
        }
    }
}
