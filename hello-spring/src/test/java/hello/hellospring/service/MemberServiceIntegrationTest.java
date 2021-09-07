package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

//    @Autowired
//    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring2");

        //when
        Long saveId = memberService.join(member);

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
        memberService.findMembers();
    }

    @Test
    void findOne() {
        Member member1 = new Member();
        Member member2 = new Member();

        Optional<String> result = Optional.ofNullable(member2.getName());
        System.out.println("result = " + result);
//        Optional.ofNullable(member2.getName())
//                .isEmpty()

//        .isEmpty(test -> System.out.println("member2.getName() = " + member2.getName()));

        Optional<String> result2 = Optional.ofNullable(member2.getName());

//        System.out.println("member2.getName() = " + member2.getName());
        System.out.println("result2 = " + result2);
    }
}