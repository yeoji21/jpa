package study.datajpa.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {

    @Autowired
    EntityManager em;

//    @Test
    void teamInsertMember(){
        Team team = new Team("teamA");
        em.persist(team);

        Member member = new Member("memberA");
        em.persist(member);

        team.getMembers().add(member);
        em.flush();
        em.clear();

        Team findTeam = em.find(Team.class, team.getId());
        findTeam.getMembers().forEach(System.out::println);

        Assertions.assertThat(findTeam.getMembers().size()).isEqualTo(0);
    }

//    @Test
    void memberInsertTeam(){
        //given
        Team team = new Team("teamA");
        em.persist(team);

        Member member = new Member("memberA");
        em.persist(member);

//        member.setTeam(team);
        member.changeTeam(team);
//        em.flush();
//        em.clear();

        //when
        Team findTeam = em.find(Team.class, team.getId());
        findTeam.getMembers().forEach(System.out::println);

        //then
        Assertions.assertThat(findTeam.getMembers().size()).isEqualTo(1);
    }


    @Test
    void test(){
        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        //when

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        members.forEach(m -> {
            System.out.println(m);
            System.out.println(m.getTeam());
        });


        //then
    }
}