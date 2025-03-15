package springstudy.boardproject.domain.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springstudy.boardproject.domain.entity.Member;
import springstudy.boardproject.domain.form.AddPostingForm;
import springstudy.boardproject.domain.entity.Posting;
import springstudy.boardproject.domain.service.BoardService;


@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/write")
    public String writeBoard(@ModelAttribute("postingForm") AddPostingForm postingForm) {
        return "write";
    }

    @PostMapping("/write")
    public String postBoard(@Validated @ModelAttribute("postingForm") AddPostingForm postingForm, BindingResult bindingResult,
                            HttpServletRequest request){
        //검증로직
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "write";
        }
        HttpSession session = request.getSession();
        Member loginMember = (Member)session.getAttribute("loginMember");

        //통과로직
        Posting post = new Posting(postingForm.getTitle(), loginMember.getUsername(), postingForm.getContent());

        boardService.save(post);
        return "redirect:/";
    }
    @GetMapping("/view/{id}")
    public String boardView(@PathVariable Long id, Model model){
        Posting findPost = boardService.findById(id);
        //찾은 게시물이 null일 경우 예외처리 로직 구상중 일단 리턴
        if(findPost== null){
            return "boardview";
        }
        model.addAttribute("post", findPost);
        return "boardview";
    }
}
