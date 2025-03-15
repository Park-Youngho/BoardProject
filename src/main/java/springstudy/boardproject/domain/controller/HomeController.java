package springstudy.boardproject.domain.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springstudy.boardproject.domain.entity.Member;
import springstudy.boardproject.domain.entity.Posting;
import springstudy.boardproject.domain.repository.BoardRepository;
import springstudy.boardproject.domain.repository.MemberRepository;
import springstudy.boardproject.domain.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final BoardService boardService;
    @GetMapping
    public String home(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                       Model model){
        boardList(model);
        if(loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    private void boardList(Model model) {
        List<Posting> posts = boardService.findAll();
        model.addAttribute("posts", posts);
    }
}
