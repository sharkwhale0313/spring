package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    //@Autowired // 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌 (의존 관계를 외부에서 넣어주는 것) -> 의존성 주입 DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 화면 매핑
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 회원 가입
    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {

        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // home 화면으로 이동
    }
}
