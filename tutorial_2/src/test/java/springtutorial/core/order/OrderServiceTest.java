package springtutorial.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springtutorial.core.member.Grade;
import springtutorial.core.member.Member;
import springtutorial.core.member.MemberService;
import springtutorial.core.member.MemberServiceImpl;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(1L, "Suyeon", Grade.VIP);
        memberService.signup(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
