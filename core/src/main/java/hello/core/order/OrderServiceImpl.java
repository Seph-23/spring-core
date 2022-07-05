package hello.core.order;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

//    변경 전
//    !!OCP 위반!! OrderServiceImpl 이 Interface 와 구현체 모두에 의존하고 있음.
//    DIP 위반하지 않도록 인터페이스에만 의존하도록 변경.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //변경 후
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;              //인터페이스에만 의존.

    //생성자 주입: 불변, 필수 의존관게에 사용. 가장 추천하는 방법.
    //수정자 주입: 선택, 변경 가능성이 있는 의존관계에 사용
    //필드 주입: 왠만하면 사용하지 말자. 외부에서 변경이 불가능하다. Configuration 같이 특이한 상황에서는 사용 가능.

    @Autowired      //생성자를 통해서만 의존관계 주입. 생성자가 한개만 있으면 Autowired 생략해도 의존관계 자동주입.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도 
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
