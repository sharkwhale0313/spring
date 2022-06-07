package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // memberService 에서 생성한 Repository == memberRepository 같은 Repository로 테스트 해야한다.
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // memberService 의 Repository 와 memberRepository가 같게 하기 위해
        // DI (Dependency Injection) 의존성 부여 사용
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // test는 한글로 네이밍하기도 한다
    @Test
    public void 회원가입() throws Exception {

        // given : candy 회원 생성
        Member member = new Member();
        member.setName("candy");

        // when : candy 회원가입
        Long saveId = memberService.join(member);

        // then : 생성한 멤버 이름 == repository 에 저장된 멤버 이름
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    // 중복 회원 가입 -> 오류 메세지 발생 확인
    @Test
    public void 중복_회원_예외() throws Exception {

        // given : 이름이 candy 로 똑같은 회원 2명
        Member member1 = new Member();
        member1.setName("candy");

        Member member2 = new Member();
        member2.setName("candy");

        // when : 이름이 똑같은 회원이 가입했을 때
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); // 예외 발생해야 함

        // then : 메세지가 같은지 확인
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}