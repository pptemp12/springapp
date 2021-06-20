package at.htlstp.pfiel.lab15.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "spieler")
public class Spieler implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spieler_id")
    private Integer id;

    @Column(name = "spieler_vorname", nullable = false)
    private String vorname;

    @Column(name = "spieler_nachname", nullable = false)
    private String nachname;

    @Column(name = "spieler_geburtsdatum", nullable = false)
    private LocalDate geburtsdatum;

    @ManyToOne
    @JoinColumn(name = "spieler_team")
    private Team team;

    public Spieler() {
    }

    public Spieler(String vorname, String nachname, LocalDate geburtsdatum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
    }

    public Spieler(String vorname, String nachname, LocalDate geburtsdatum, Team team) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public Team getTeam() {
        return team;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spieler spieler = (Spieler) o;
        return Objects.equals(id, spieler.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Spieler{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                ", team=" + team +
                '}';
    }
}
