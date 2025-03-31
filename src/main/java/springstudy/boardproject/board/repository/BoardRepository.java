package springstudy.boardproject.board.repository;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import springstudy.boardproject.board.entity.Board;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository{
    private final EntityManager em;
    public Long save(Board post) {
        em.persist(post);
        return post.getId();
    }

    public List<Board> findAll() {
        List<Board> Postings = em.createQuery("select p from Board p", Board.class).getResultList();
        return Postings;
    }

    public Board findById(Long id) {
        Board findPost = em.find(Board.class, id);
        return findPost;
    }

    //변경감지를 통한 업데이트
    public void update(Long id, String title, String content) {
        Board findPost = findById(id);
        findPost.setTitle(title);
        findPost.setContent(content);
    }

    public void delete(Long id){
        Board findPost = findById(id);
        em.remove(findPost);
    }

}
