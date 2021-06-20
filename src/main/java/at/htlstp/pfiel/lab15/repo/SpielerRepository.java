package at.htlstp.pfiel.lab15.repo;

import at.htlstp.pfiel.lab15.model.Spieler;
import at.htlstp.pfiel.lab15.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SpielerRepository extends JpaRepository<Spieler, Integer> {
    List<Spieler> findSpielerByGeburtsdatumBetween(LocalDate d1, LocalDate d2);

    List<Spieler> findSpielerByGeburtsdatumAfter(LocalDate d1);

    List<Spieler> findSpielerByGeburtsdatumBefore(LocalDate d1);

    List<Spieler> findSpielerByTeam_Kuerzel(String kuerzel);

    Optional<Spieler> findSpielerByNachnameAndVorname(String nachname, String vorname);


}
