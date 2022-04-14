package springtutorial.core.member;

public interface MemberService {
    void signup(Member member);
    Member findMember(Long memberId);
}
