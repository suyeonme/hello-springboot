package suyeon.hello_spring.repository;

import org.springframework.stereotype.Repository;
import suyeon.hello_spring.domain.Member;

import java.util.*;

// DB에 데이터 저장
// 아직 DB가 선정되지 않았을 경우 가정 (interface로 구현체 정의) -> 추후에 교체

//@Repository
// AutoWired로 연결
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Optional.ofNullable: null일 수 있음
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        // Test에서 afterEach에 사용
        store.clear();
    }
}
