package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//아직 DB가 결정되지 않았다는 가정 하에 임시로 MemberRepository를 만든 것이므로
//나중에 DB가 결정되면 구현체를 바꿀 때 수월하게 하기 위해
//@Service, @Repository, @Autowired 어노테이션을 쓰지 않고
//자바코드로 직접 Spring Bean 을 등록했다.

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // 새로 구현한 Repository로 바꿔끼움. 다형성
        return new JdbcMemberRepository(dataSource);
    }
}
