package springstudy.boardproject.domain.repository;


import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.boardproject.domain.entity.Posting;

@Repository
public interface BoardRepository extends JpaRepository<Posting, Long>{
}
