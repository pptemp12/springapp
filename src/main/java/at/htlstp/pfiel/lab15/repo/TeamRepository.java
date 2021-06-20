package at.htlstp.pfiel.lab15.repo;

import at.htlstp.pfiel.lab15.model.Spieler;
import at.htlstp.pfiel.lab15.model.Team;
import at.htlstp.pfiel.lab15.model.Turnier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, String> {

    @Query("SELECT t.teams from Turnier t where t.id = :turnierId")
    List<Team> findTeamsByTurnier(@Param("turnierId") Integer turnierId);

    Team findTeamBySpieler(Spieler s);

    List<Team> findTeamByNameContains(String name);
}
