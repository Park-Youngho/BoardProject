package springstudy.boardproject.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.boardproject.member.entity.Member;
import springstudy.boardproject.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member);
    }


}
