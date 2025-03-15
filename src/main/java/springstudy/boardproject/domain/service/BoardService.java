package springstudy.boardproject.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springstudy.boardproject.domain.entity.Posting;
import springstudy.boardproject.domain.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(Posting post){
        boardRepository.save(post);
    }
    public List<Posting> findAll(){
        return boardRepository.findAll();
    }
    public Posting findById(Long id){
        return boardRepository.findById(id).get();
    }
}
