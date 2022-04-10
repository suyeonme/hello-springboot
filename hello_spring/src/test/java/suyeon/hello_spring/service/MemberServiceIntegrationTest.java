package suyeon.hello_spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import suyeon.hello_spring.domain.Member;
import suyeon.hello_spring.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

// 2. 스프링을 이용한 테스트

@SpringBootTest
//@Transactional


class MemberServiceIntegrationTest {
    @Autowired  MemberService memberService;
    @Autowired  MemberRepository memberRepository;

    @Test
    void join() {
        // 회원가입
        Member member = new Member();
        member.setName("Suyeon");

        Long savedId = memberService.join(member);

        Member findMember = memberService.findOne(savedId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplicated_join_exception() {
        // 회원가입 예외
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("Suyeon");
        member2.setName("Suyeon");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}