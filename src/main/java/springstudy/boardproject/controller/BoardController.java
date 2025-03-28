package springstudy.boardproject.controller;

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
import springstudy.boardproject.service.BoardService;


@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //글작성 화면
    @GetMapping("/write")
    public String writeBoard(@ModelAttribute("postingForm") AddPostingForm postingForm) {
        return "write";
    }
    //글작성후 제출
    @PostMapping("/write")
    public String submitBoard(@Validated @ModelAttribute("postingForm") AddPostingForm postingForm, BindingResult bindingResult,
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
    //글 상세보기
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

    //글 수정 폼
    @GetMapping("/modify/{id}")
    public String modifyBoard(@PathVariable("id") Long id, Model model){
        Posting findPost = boardService.findById(id);
        model.addAttribute("post", findPost);

        return "boardmodify";
    }
    @PostMapping("/modify/{id}")
    public String submitModify(@PathVariable("id") Long id,
                               @Validated @ModelAttribute("post") AddPostingForm modifyPost,BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            log.info("error ={}", bindingResult);
            return "boardmodify";
        }
        boardService.update(id, modifyPost);
        return "redirect:/view/" + id; // 수정 후 글 상세보기로 이동
    }
}
