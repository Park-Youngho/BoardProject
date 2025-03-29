package springstudy.boardproject.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Posting {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String username;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String content;

    public Posting(){

    }

    public Posting(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.createDate = LocalDateTime.now();
        this.content = content;
    }
}
