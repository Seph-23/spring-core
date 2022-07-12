package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    //프로토타입 빈의 특징 정리:
    //프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회할때 생성되고, 초기화 메서드도 실행된다.
    //싱글톤 빈은 스프링 컨테이너가 관리하기 때문에 스프링 컨테이너가 정료될 때 빈의 종료 매서드가 실행되지만
    //프로로타입 빈 스프링 컨테이너가 종료될때 @PreDestroy 같은 종료 매서드가 전혀 실행되지 않는다.

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        prototypeBean1.destroy();           //수동으로 죽여야함
        prototypeBean2.destroy();           //만약 필요하다면.

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
