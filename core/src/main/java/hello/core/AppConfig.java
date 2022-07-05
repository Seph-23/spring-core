package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//AppConfig 자체를 IoC 컨테이너 또는 DI 컨테이너라고 부른다.
@Configuration
public class AppConfig {            //애플리케이션의 전체 동작 방식을 구성하기위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 설정 클래스.
                                    //생성자 주입.

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //싱글톤이 깨지는 것 처럼 보이는데, 스프링 컨테이너는 과연 이걸 어떻게 해결해줄까?

    //Dependency Injection / 의존관계 주입
    @Bean           //@Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다 ex) memberService
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //이제 할인 정책을 변경해도 구성 역할을 담당하는 AppConfig 만 변경하면 된다.
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }
}
