package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//간단하게 테스트 해보는 방법.
public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();

        //변경 전
        //MemberService memberService = new MemberServiceImpl();

        //변경 후, 의존성 주입.
        //MemberService memberService = appConfig.memberService();

        //스프링 사용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);    //AppConfig 에 @Bean 이 붙은 애들은 다 생성해서 관리한다
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
