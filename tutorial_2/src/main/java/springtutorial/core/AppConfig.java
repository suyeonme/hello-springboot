package springtutorial.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springtutorial.core.discount.DiscountPolicy;
import springtutorial.core.discount.FixDiscountPolicy;
import springtutorial.core.discount.RateDiscountPolicy;
import springtutorial.core.member.MemberRepository;
import springtutorial.core.member.MemberService;
import springtutorial.core.member.MemberServiceImpl;
import springtutorial.core.member.MemoryMemberRepository;
import springtutorial.core.order.OrderService;
import springtutorial.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
