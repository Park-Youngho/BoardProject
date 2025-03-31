package springstudy.boardproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.boardproject.board.entity.Board;
import springstudy.boardproject.board.dto.AddPostingForm;
import springstudy.boardproject.board.repository.BoardRepository;
import springstudy.boardproject.board.repository.PageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final PageRepository pageRepository;
    @Transactional
    public Long save(Board post){
        return boardRepository.save(post);
    }
    
    //읽기 전용
    @Transactional(readOnly = true)
    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    //읽기전용
    @Transactional(readOnly = true)
    public Board findById(Long id){
        return boardRepository.findById(id);
    }

    @Transactional
    public void update(Long postId, AddPostingForm modifyPost){
        boardRepository.update(postId, modifyPost.getTitle(), modifyPost.getContent());
    }

    @Transactional(readOnly = true)
    public Page<Board> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return pageRepository.findAll(pageable);
    }
    @Transactional
    public void plusViewCount(Long id){
        Board findPost = findById(id);
        findPost.viewCountUp();
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.delete(id);
    }

}
