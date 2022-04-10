package suyeon.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suyeon.hello_spring.domain.Member;

import java.util.Optional;

// Spring-data-jpa가 인터페이스의 구현체를 생성한뒤 스프링빈으로 등록한다. (JpaRepository)
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
