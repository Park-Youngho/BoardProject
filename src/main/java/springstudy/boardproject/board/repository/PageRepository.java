package springstudy.boardproject.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.boardproject.board.entity.Board;

public interface PageRepository extends JpaRepository<Board, Long> {
    Page<Board> findAll(Pageable pageable);
}
