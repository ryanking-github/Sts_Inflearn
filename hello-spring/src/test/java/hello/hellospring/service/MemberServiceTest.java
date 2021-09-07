package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.assertj.core.api.Assertions.*;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring2");

        //when
        Long saveId = memberService.join(member);
//        member.setName("hello3");
//        memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("spring");
        member2.setName("spring");

        Long saveId1 = memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//            Long saveId2 = memberService.join(member2);
//            fail("이미 존재하는 회원입니다.");
//        }catch(IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
        Member member1 = new Member();
        Member member2 = new Member();

        Optional<String> result = Optional.ofNullable(member2.getName());

//        Optional.ofNullable(member2.getName())
//                .isEmpty()

//        .isEmpty(test -> System.out.println("member2.getName() = " + member2.getName()));

        Optional<String> result2 = Optional.ofNullable(member2.getName());

//        System.out.println("member2.getName() = " + member2.getName());
        System.out.println("result2 = " + result2);
    }

    @Test
    void 삼각형찍기() {

        for(int i = 0; i<5 ; i++) {
            for(int j= 5 - i; j>0; j--) {
                System.out.print("헙");
            }
            System.out.print("\n");
        }
    }
}