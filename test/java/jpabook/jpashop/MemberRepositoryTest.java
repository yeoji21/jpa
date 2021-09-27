package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
//    @Test
//    @Transactional
//    @Rollback(false)
//    void testMember(){
//        Member member = new Member();
//        member.setUsername("test2");
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.find(saveId);
//
//        assertEquals(member.getId(), findMember.getId());
//        assertEquals(member.getUsername(), findMember.getUsername());
//        assertEquals(member, findMember);
//    }


}