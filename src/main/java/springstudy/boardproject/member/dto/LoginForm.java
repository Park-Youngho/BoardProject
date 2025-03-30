package springstudy.boardproject.member.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter @Setter
@Component
public class LoginForm {
    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
}
