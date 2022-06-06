package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드 실행이 끝날때마다 실행되는 callable 메서드
    public void afterEach() {
        repository.clearStore(); // 이전 메소드의 테스트 결과가 남아있지 않도록 메모리 클리어
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        // given : member1, member2 회원가입
        Member member1 = new Member();
        member1.setName("candy");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("choco");
        repository.save(member2);

        // when : "candy" 라는 name으로 findByName
        Member result = repository.findByName("candy").get();

        // then : member1과 findByName의 결과가 같은지 확인
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // given : 멤버 2명 가입
        Member member1 = new Member();
        member1.setName("candy");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("choco");
        repository.save(member2);

        // when : findAll
        List<Member> result = repository.findAll();

        // then : 리스트 길이 2
        assertThat(result.size()).isEqualTo(2);

    }
}
