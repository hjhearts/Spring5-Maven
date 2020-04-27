package ch11.controller;

import ch11.servey.AnsweredData;
import ch11.servey.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    @GetMapping
    public String form(Model model){
        List<Question> questions = createQuestions();
        model.addAttribute("questions", questions);
        return "survey/surveyForm";
    }

    private List<Question> createQuestions(){
        Question q1 = new Question("당신의 역할은 무엇입니까?", Arrays.asList("서버", "프론트", "풀스택"));
        Question q2 = new Question("많이 사용하는 개발도구는?", Arrays.asList("Eclipse", "IntelliJ", "Sublime"));
        Question q3 = new Question("추가로 남길 말");
        return Arrays.asList(q1, q2, q3);
    }

    @PostMapping
    public String submit(@ModelAttribute("ansData")AnsweredData data){
        return "survey/submitted";
    }
}
