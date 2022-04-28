package springtutorial.core.order;

import springtutorial.core.discount.DiscountPolicy;
import springtutorial.core.discount.FixDiscountPolicy;
import springtutorial.core.discount.RateDiscountPolicy;
import springtutorial.core.member.Member;
import springtutorial.core.member.MemberRepository;
import springtutorial.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
