package springstudy.boardproject.updatetest;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springstudy.boardproject.domain.entity.Posting;
import springstudy.boardproject.domain.form.AddPostingForm;
import springstudy.boardproject.service.BoardService;

@SpringBootTest
@Transactional
public class PostingUpdateTest {
    @Autowired EntityManager em;
    @Autowired BoardService boardService;

    @Test
    public void update() throws Exception{
        //given
        Posting post = new Posting("테스트1", "번개맨", "테스트 내용");
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
        Posting findPost = boardService.findById(id);

        //기존의 내용과 업데이트한 게시물의 내용이 달라졌는지 테스트
        Assertions.assertThat(testTitle).isNotEqualTo(findPost.getTitle());
        Assertions.assertThat(testContent).isNotEqualTo(findPost.getTitle());


    }
}
