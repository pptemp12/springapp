package at.htlstp.pfiel.lab15.repo;

import at.htlstp.pfiel.lab15.model.Team;
import at.htlstp.pfiel.lab15.model.Turnier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TurnierRepository extends JpaRepository<Turnier, Integer> {
    List<Turnier> findTurnierByDateBefore(LocalDate d);

    List<Turnier> findTurnierByDateAfter(LocalDate d);

    List<Turnier> findTurnierByDateBetween(LocalDate d1, LocalDate d2);

    List<Turnier> findTurnierByNameContains(String name);

    List<Turnier> findTurnierByOrtContains(String ort);
}
