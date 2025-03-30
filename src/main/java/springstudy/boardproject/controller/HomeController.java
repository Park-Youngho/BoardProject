package springstudy.boardproject.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springstudy.boardproject.member.entity.Member;
import springstudy.boardproject.board.entity.Board;
import springstudy.boardproject.board.service.BoardService;

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
        Page<Board> paging = boardService.getList(page);
        model.addAttribute("posts", paging);
        if(loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }


}
