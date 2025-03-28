package springstudy.boardproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.boardproject.domain.entity.Posting;
import springstudy.boardproject.domain.form.AddPostingForm;
import springstudy.boardproject.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Posting post){
        return boardRepository.save(post);
    }
    
    //읽기 전용
    @Transactional(readOnly = true)
    public List<Posting> findAll(){
        return boardRepository.findAll();
    }

    //읽기전용
    @Transactional(readOnly = true)
    public Posting findById(Long id){
        return boardRepository.findById(id);
    }

    @Transactional
    public void update(Long postId, AddPostingForm modifyPost){
        boardRepository.update(postId, modifyPost.getTitle(), modifyPost.getContent());
    }

//    @Transactional
//    public void delete(Long id){
//        boardRepository.delete(id);
//    }
}
