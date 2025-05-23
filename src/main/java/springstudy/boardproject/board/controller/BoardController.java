package springstudy.boardproject.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springstudy.boardproject.member.entity.Member;
import springstudy.boardproject.board.dto.AddPostingForm;
import springstudy.boardproject.board.entity.Board;
import springstudy.boardproject.board.service.BoardService;


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
        Board post = new Board(postingForm.getTitle(), postingForm.getContent(), loginMember);

        boardService.save(post);
        return "redirect:/";
    }
    //글 상세보기
    @GetMapping("/view/{id}")
    public String boardView(@PathVariable Long id, Model model, HttpServletRequest request){
        Board findPost = boardService.findById(id);
        boardService.plusViewCount(id); // 상세보기를 누르면 조회수 오르게 작성
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
        Board findPost = boardService.findById(id);
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
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/";
    }
}
