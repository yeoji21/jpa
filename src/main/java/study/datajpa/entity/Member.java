package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@ToString(of={"id","username","age"})
public class
Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private int age;
    private String username;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="team_id")     //team_id가 FK가 된다.
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.age = age;
        this.username = username;
        changeTeam(team);
    }

    public Member(String username, int age) {
        this.age = age;
        this.username = username;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
