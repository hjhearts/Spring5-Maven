package ch03.main;

import ch03.spring.*;
import ch03.assembler.Assembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {
    public static void main(String[] args) throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("명령어를 입력하세요.");
            String command = bufReader.readLine();
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
            }
            printHelp();
        }
    }

    private static Assembler assembler = new Assembler();

    private static void processNewCommand(String[] args){
        if(args.length != 5){
            printHelp();
            return;
        }
        MemberRegisterService registerService = assembler.getMemberRegisterService();
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
        ChangePasswordService changePasswordService = assembler.getChangePasswordService();
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
        System.out.println();
    }
}
