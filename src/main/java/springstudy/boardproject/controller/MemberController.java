package springstudy.boardproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springstudy.boardproject.domain.entity.Member;
import springstudy.boardproject.domain.form.AddMemberForm;
import springstudy.boardproject.repository.MemberRepository;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberRepository memberRepository;
    @GetMapping("/sign")
    public String loadingSignPage(@ModelAttribute("addMemberForm") AddMemberForm addMemberForm){
        return "sign";
    }

    @PostMapping("/sign")
    public String sign(@Validated @ModelAttribute("addMemberForm") AddMemberForm addMemberForm, BindingResult bindingResult){
        //검증 실패시 다시 같은 페이지
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "sign";
        }
        log.info("errors={}", bindingResult);
        Member member = new Member(addMemberForm.getUserId(), addMemberForm.getPassword(), addMemberForm.getUsername());
        memberRepository.save(member);
        return "redirect:/login";
    }
}
