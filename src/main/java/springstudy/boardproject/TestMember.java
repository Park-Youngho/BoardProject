package springstudy.boardproject;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springstudy.boardproject.domain.entity.Member;
import springstudy.boardproject.domain.entity.Posting;
import springstudy.boardproject.service.BoardService;
import springstudy.boardproject.service.MemberService;

@RequiredArgsConstructor
@Component
public class TestMember {
    private final MemberService memberService;
    private final BoardService boardService;

    @PostConstruct
    public void init(){
        Member member = new Member("test", "test1", "test");
        Member member2 = new Member("ajk1330", "1234", "번개맨");
        Posting post1 = new Posting("테스트1", "번개맨", "테스트 내용");
        Posting post2 = new Posting("테스트2", "테스트맨", "테스트내용2");

        boardService.save(post1);
        boardService.save(post2);
        memberService.save(member);
        memberService.save(member2);
    }
}
