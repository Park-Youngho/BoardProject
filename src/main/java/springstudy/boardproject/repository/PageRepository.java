package springstudy.boardproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.boardproject.domain.entity.Posting;

public interface PageRepository extends JpaRepository<Posting, Long> {
    Page<Posting> findAll(Pageable pageable);
}
