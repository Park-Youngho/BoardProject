package springstudy.boardproject.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Entity
public class Posting {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String username;

    private LocalDate createDate;
    private LocalDate modifiedDate;
    private String content;

    public Posting(){

    }

    public Posting(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.createDate = LocalDate.now();
        this.content = content;
    }
}
