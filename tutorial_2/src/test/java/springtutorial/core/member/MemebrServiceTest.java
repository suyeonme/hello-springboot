package springtutorial.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemebrServiceTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    void signup() {
        // Given
        Member member = new Member(1L, "Suyeon", Grade.BASIC);
        // Where
        memberService.signup(member);
        Member foundMember = memberService.findMember(1L);
        // Then
        Assertions.assertThat(member).isEqualTo(foundMember);
    }
}