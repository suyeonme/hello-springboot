package suyeon.hello_spring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import suyeon.hello_spring.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Suyeon");
        repository.save(member);
        // optional타입에서 값을 꺼낼때는 get 사용
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findById() {
        Member member1 = new Member();
        member1.setName("Hanna");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Hanna");
        repository.save(member2);

        Member result = repository.findById(member1.getId()).get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Hanna");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Hanna");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
