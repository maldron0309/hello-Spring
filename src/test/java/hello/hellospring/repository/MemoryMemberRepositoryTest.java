package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        memoryMemberRepository.save(member);

        // member의 id 값으로, 저장소에 저장된 객체 result 가져옴
        // get(): 값을 꺼내옴
        Member result = memoryMemberRepository.findById(member.getId()).get();

        // 저장한 member와 저장소에서 가져온 result가 같은 지 검증
        System.out.println("result = " + (result == member));
        assertEquals(member, result);
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1); // spring1 저장

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2); // spring2 저장

        Member result = memoryMemberRepository.findByName("spring1").get(); // spring1 이라는 회원을 찾음
        assertThat(result).isEqualTo(member1); // 찾은 회원이 member1과 동일한지 확인
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);

        List<Member> result = memoryMemberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}


