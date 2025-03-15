package springstudy.boardproject.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springstudy.boardproject.domain.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
