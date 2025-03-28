package springstudy.boardproject.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springstudy.boardproject.domain.entity.Member;
import springstudy.boardproject.domain.form.LoginForm;
import springstudy.boardproject.service.LoginService;
import springstudy.boardproject.repository.MemberRepository;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final MemberRepository memberRepository;
    private final LoginService loginService;
    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm){

        return "login";
    }

    @PostMapping("/login")
    public String login2(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult,
                         HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURL){
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "login";
        }
        Member loginMember = (Member)loginService.login(loginForm);
        if(loginMember== null){
            bindingResult.reject("loginError", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("loginMember", loginMember);
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
