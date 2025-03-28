package springstudy.boardproject.repository;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springstudy.boardproject.domain.entity.Member;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository{
    private final EntityManager em;

    public List<Member> findAll() {
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        return members;
    }

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }
}
