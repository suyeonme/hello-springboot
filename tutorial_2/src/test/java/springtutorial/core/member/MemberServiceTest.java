package springtutorial.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springtutorial.core.AppConfig;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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