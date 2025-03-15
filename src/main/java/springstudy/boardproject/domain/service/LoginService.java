package springstudy.boardproject.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springstudy.boardproject.domain.entity.Member;
import springstudy.boardproject.domain.form.LoginForm;
import springstudy.boardproject.domain.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Object login(LoginForm loginForm){
        List<Member> memberList = memberRepository.findAll();
        for (Member member : memberList) {
            if(member.getLoginId().equals(loginForm.getUserId())&&
            member.getPassword().equals(loginForm.getPassword())){
                return member;
            }
        }
        return null;
    }
}
