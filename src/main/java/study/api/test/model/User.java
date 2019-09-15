package study.api.test.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    private String userName;

    protected User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public void setTeam(Team team) {
        if (this.team != null) {
            this.team.getUsers().remove(this);
        }
        this.team = team;
        team.getUsers().add(this);
    }
}
