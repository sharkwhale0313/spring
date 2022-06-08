package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired // 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌 (의존 관계를 외부에서 넣어주는 것) -> 의존성 주입 DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
