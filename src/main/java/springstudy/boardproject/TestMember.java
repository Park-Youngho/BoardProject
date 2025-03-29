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
        for(int i=0; i<100; i++){
            Posting post = new Posting("테스트"+i, "테스트맨" + i, "테스트내용" + i);
            boardService.save(post);
        }
        Member member = new Member("test", "test1", "test");
        Member member2 = new Member("ajk1330", "1234", "test");
        memberService.save(member);
        memberService.save(member2);
    }
}
