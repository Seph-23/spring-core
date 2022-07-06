package hello.core.member;

public interface MemberService {

    //추상 메소드 선언
    void join(Member member);
    Member findMember(Long memberId);
}
