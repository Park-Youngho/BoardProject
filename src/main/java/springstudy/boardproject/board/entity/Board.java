package springstudy.boardproject.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import springstudy.boardproject.member.entity.Member;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Board {
    @Id @GeneratedValue
    private Long id;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String content;
    private int viewCount;
    public Board(){

    }
    public Board(String title, String content, Member member) {
        this.title = title;
        this.member = member;
        this.createDate = LocalDateTime.now();
        this.content = content;
    }
    public void viewCountUp(){
        this.viewCount++;
    }
}
