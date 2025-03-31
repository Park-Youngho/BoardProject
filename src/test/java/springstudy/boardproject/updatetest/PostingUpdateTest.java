package springstudy.boardproject.updatetest;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springstudy.boardproject.board.entity.Board;
import springstudy.boardproject.board.dto.AddPostingForm;
import springstudy.boardproject.board.service.BoardService;
import springstudy.boardproject.member.entity.Member;

@SpringBootTest
@Transactional
public class PostingUpdateTest {
    @Autowired EntityManager em;
    @Autowired BoardService boardService;

    @Test
    public void update() throws Exception{
        //given
        Member member = new Member("test2", "test2", "test2");
        Board post = new Board("테스트123", "내영", member);
        String testTitle = post.getTitle();
        String testContent = post.getContent();
        Long id = boardService.save(post);
        AddPostingForm addPostingForm = new AddPostingForm();
        addPostingForm.setTitle("테스트1을 변경한 제목");
        addPostingForm.setContent("테스트내용변경");

        //when
        boardService.update(post.getId(), addPostingForm);
        em.flush();
        em.clear(); //테스트를 위해 엔티티매니저 비우기
        //then
        Board findPost = boardService.findById(id);

        //기존의 내용과 업데이트한 게시물의 내용이 달라졌는지 테스트
        Assertions.assertThat(testTitle).isNotEqualTo(findPost.getTitle());
        Assertions.assertThat(testContent).isNotEqualTo(findPost.getTitle());


    }
}
