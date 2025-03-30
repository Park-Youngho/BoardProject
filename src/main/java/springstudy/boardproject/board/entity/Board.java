package springstudy.boardproject.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Board {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String username;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String content;

    public Board(){

    }

    public Board(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.createDate = LocalDateTime.now();
        this.content = content;
    }
}
