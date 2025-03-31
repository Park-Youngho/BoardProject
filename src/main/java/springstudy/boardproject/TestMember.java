package springstudy.boardproject;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springstudy.boardproject.member.entity.Member;
import springstudy.boardproject.board.entity.Board;
import springstudy.boardproject.board.service.BoardService;
import springstudy.boardproject.member.service.MemberService;

@RequiredArgsConstructor
@Component
public class TestMember {
    private final MemberService memberService;
    private final BoardService boardService;

    @PostConstruct
    public void init(){
        Member member = new Member("test", "test1", "test");
        Member member2 = new Member("ajk1330", "1234", "test");
        memberService.save(member);
        memberService.save(member2);
        for(int i=0; i<100; i++){
            Board post = new Board("테스트"+i, "테스트맨" + i, member2);
            boardService.save(post);
        }

    }
}
