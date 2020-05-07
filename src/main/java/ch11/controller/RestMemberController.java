package ch11.controller;

import ch11.spring.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class RestMemberController {
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MemberRegisterService registerService;

    @GetMapping("/api/members")
    public List<Member> members(){
        return memberDAO.selectAll();
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<Object> member(@PathVariable("id") Long id) {
        Member member = memberDAO.selectById(id);
        if(member == null){
            throw new MemberNotFoundException();
        }
        return ResponseEntity.ok(member);
    }

    @PostMapping("/api/members")
    public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest regReq, Errors errors) {
        if(errors.hasErrors()){
            String errorCode = errors.getAllErrors().stream().map(error -> Objects.requireNonNull(error.getCodes())[0])
                    .collect(Collectors.joining(","));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCode));
        }
        try{
            Long newId = registerService.regist(regReq);
            URI uri = URI.create("/api/members/" + newId);
            return ResponseEntity.created(uri).build();
        }catch(DuplicateMemberException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(WrongPasswordException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
/*
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoData(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
    }

 */
}
