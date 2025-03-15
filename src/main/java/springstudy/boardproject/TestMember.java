package springstudy.boardproject;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springstudy.boardproject.domain.entity.Member;
import springstudy.boardproject.domain.entity.Posting;
import springstudy.boardproject.domain.repository.BoardRepository;
import springstudy.boardproject.domain.repository.MemberRepository;

@RequiredArgsConstructor
@Component
public class TestMember {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @PostConstruct
    public void init(){
        Member member = new Member("test", "test1", "test");
        Member member2 = new Member("ajk1330", "1234", "번개맨");
        Posting post1 = new Posting("테스트1", "번개맨", "테스트 내용");
        Posting post2 = new Posting("테스트2", "테스트맨", "테스트내용2");

        boardRepository.save(post1);
        boardRepository.save(post2);
        memberRepository.save(member);
        memberRepository.save(member2);
    }
}
