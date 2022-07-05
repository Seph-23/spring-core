package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //컴포넌트 스캔을 하는데 @Configuration 이 붙은 애들은 제외한다.
        //기존 예제 코드를 유지하고 사용하기 위해서. 실무에서는 보통 제외하지 않는다.
//        basePackages = "hello.core.member",                     //member 패키지와 하위 패키지만 탐색.
//        basePackageClasses = AutoAppConfig.class,               //hello.core 탐색
        //지정하지 않으면 hello.core 에서 시작해서 그 하위 패키지 전부 탐색.
        //권장하는 방법: 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 제공한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //수동 빈 등록, 자동 빈 등록 오류시 스프링 부트 에러: CoreApplication 실행시 발생.
    // The bean 'memoryMemberRepository', defined in class path resource
    // [hello/core/AutoAppConfig.class], could not be registered.
    // A bean with that name has already been defined in file
    // [/Users/seph/Documents/Dev/core/out/production/classes
    // /hello/core/member/MemoryMemberRepository.class] and overriding is disabled.
    /*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
     */
}
