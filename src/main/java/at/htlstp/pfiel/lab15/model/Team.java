package at.htlstp.pfiel.lab15.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "team")
public class Team implements Serializable {
    @Id
    @Column(name = "team_kuerzel", nullable = false)
    @Size(min = 4, max = 4)
    private String kuerzel;

    @Column(name = "team_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Spieler> spieler = new ArrayList<>();

    public Team() {
    }

    public Team(String kuerzel, String name) {
        this.kuerzel = kuerzel;
        this.name = name;
    }

    public void addSpieler(Spieler s){
        this.spieler.add(s);
    }

    public void removeSpieler(Spieler s){
        this.spieler.remove(s);
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public String getName() {
        return name;
    }

    /*
    public List<Spieler> getSpieler() {
        return spieler;
    }

     */

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(kuerzel, team.kuerzel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kuerzel);
    }

    @Override
    public String toString() {
        return "Team{" +
                "kuerzel='" + kuerzel + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
