package springstudy.boardproject.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String loginId;
    private String password;
    private String username;


    private LocalDate createdDate;
    private LocalDate modifiedDate;


    public Member() {
    }

    public Member(String loginId, String password, String username) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
    }
}