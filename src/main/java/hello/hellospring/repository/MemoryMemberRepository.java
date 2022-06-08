package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 회원정보 저장
    private static long sequence = 0L; // 회원 id 값 부여

    @Override
    public Member save(Member member) { // 회원정보 저장
        member.setId(++sequence); // id 생성
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // ofNullable : Null 이어도 감쌀 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 같은 경우에만 filtering. 찾으면 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // key(id값), value(객체) 중 value만 반환
    }

    public void clearStore() {
        store.clear();
    }
}
