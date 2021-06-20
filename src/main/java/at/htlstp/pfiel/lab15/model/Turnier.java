package at.htlstp.pfiel.lab15.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "turnier")
public class Turnier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turnier_id")
    private Integer id;

    @ManyToMany
    @JoinTable(name = "turnier_team"
            , joinColumns = {@JoinColumn(name = "turnier_id")}
            , inverseJoinColumns = {@JoinColumn(name = "team_kuerzel")})
    private List<Team> teams = new ArrayList<>();

    @Column(name = "turnier_date", nullable = false)
    private LocalDate date;

    @Column(name = "turnier_ort", nullable = false)
    private String ort;

    @Column(name = "turnier_name", nullable = false)
    private String name;

    public Turnier() {
    }

    public Turnier(LocalDate date, String ort, String name) {
        this.date = date;
        this.ort = ort;
        this.name = name;
    }

    public void addTeam(Team t){
        this.teams.add(t);
    }
    public void remove(Team t){
        this.teams.remove(t);
    }

    public Integer getId() {
        return id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getOrt() {
        return ort;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turnier turnier = (Turnier) o;
        return Objects.equals(id, turnier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Turnier{" +
                "id=" + id +
                ", teams=" + teams +
                ", date=" + date +
                '}';
    }
}
