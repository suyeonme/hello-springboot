package springtutorial.core.discount;

import springtutorial.core.member.Grade;
import springtutorial.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
