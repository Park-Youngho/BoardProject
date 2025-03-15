package springstudy.boardproject.domain.repository;


import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.boardproject.domain.entity.Posting;

import java.util.List;
import java.util.concurrent.Future;

@Repository
@RequiredArgsConstructor
public class BoardRepository{
    private final EntityManager em;
    public Long save(Posting post) {
        em.persist(post);
        return post.getId();
    }

    public List<Posting> findAll() {
        List<Posting> Postings = em.createQuery("select p from Posting p", Posting.class).getResultList();
        return Postings;
    }

    public Posting findById(Long id) {
        Posting findPost = em.find(Posting.class, id);
        return findPost;
    }

    //변경감지를 통한 업데이트
    public void update(Long id, String title, String content) {
        Posting findPost = findById(id);
        findPost.setTitle(title);
        findPost.setContent(content);
    }

}
