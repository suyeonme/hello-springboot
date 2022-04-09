package suyeon.hello_spring.service;

// 비즈니스 로직

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suyeon.hello_spring.domain.Member;
import suyeon.hello_spring.repository.MemberRepository;
import suyeon.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Service
// Autowired로 연결
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        // DB객체(repository)가 여러개의 인스턴트가 생성되지 않도록
        // Dipendency Injection
        this.memberRepository = memberRepository;
    }

    /* 회원가입 */
    public Long join(Member member) {
        validateDuplicatedMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        // 중복 회원 검증
        memberRepository.findByName(member.getName()).ifPresent(r -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
