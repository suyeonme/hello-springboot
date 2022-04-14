package suyeon.hello_spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import suyeon.hello_spring.domain.Member;
import suyeon.hello_spring.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;

// 1. 순수 자바를 이용한 테스트
class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // DB객체가 여러개 생성되지 않도록
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        // 테스트시 DB값 초기화
        memberRepository.clearStore();
    }

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

//        try {
//            memberService.join(member2);
//            fail();
//        } catch(IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}