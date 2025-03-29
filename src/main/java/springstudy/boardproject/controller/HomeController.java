package springstudy.boardproject.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springstudy.boardproject.domain.entity.Member;
import springstudy.boardproject.domain.entity.Posting;
import springstudy.boardproject.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final BoardService boardService;
    @GetMapping
    public String home(@RequestParam(value = "page", defaultValue ="0") int page,
            @SessionAttribute(name = "loginMember", required = false) Member loginMember,
                       Model model){
        Page<Posting> paging = boardService.getList(page);
        model.addAttribute("posts", paging);
        //boardList(model);
        if(loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    private void makePageList(int page, Model model) {
        Page<Posting> paging = boardService.getList(page);
        model.addAttribute("posts", paging);
    }

//    private void boardList(Model model) {
//        List<Posting> posts = boardService.findAll();
//        model.addAttribute("posts", posts);
//    }
}
