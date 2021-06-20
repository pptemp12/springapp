package at.htlstp.pfiel.lab15.web;

import at.htlstp.pfiel.lab15.error.*;
import at.htlstp.pfiel.lab15.model.Spieler;
import at.htlstp.pfiel.lab15.model.Turnier;
import at.htlstp.pfiel.lab15.repo.TurnierRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class TurnierController {
    private final TurnierRepository turnierRepository;

    public TurnierController(TurnierRepository repository) {
        this.turnierRepository = repository;
    }

    @GetMapping("/turniere")
    public List<Turnier> getAllTurniere() {
        return turnierRepository.findAll();
    }

    @GetMapping("/turnier/{id}")
    public Turnier getTurnierById(@PathVariable Integer id) {
        return turnierRepository.findById(id).orElseThrow(() -> new TurnierNotFoundException("Turnier not found with ID: " + id));
    }

    @GetMapping("/turnier/date")
    public List<Turnier> getTurniereBetweenBeginAndEnd(@RequestParam(name = "begin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin, @RequestParam(name = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        if (begin == null && end == null) {
            return turnierRepository.findAll();
        }
        if (begin == null) {
            return turnierRepository.findTurnierByDateBefore(end);
        }
        if (end == null) {
            return turnierRepository.findTurnierByDateAfter(begin);
        }
        if (begin.isAfter(end)) {
            throw new InvalidDateException("Begin-Date can't be before end-date!");
        }
        return turnierRepository.findTurnierByDateBetween(begin, end);
    }

    @GetMapping("/turnier/name/{name}")
    public List<Turnier> getTurniereByNameContains(@PathVariable String name) {
        return turnierRepository.findTurnierByNameContains(name);
    }

    @GetMapping("/turnier/ort/{ort}")
    public List<Turnier> getTurniereByOrtContains(@PathVariable String ort) {
        return turnierRepository.findTurnierByOrtContains(ort);
    }

    @PostMapping("/turnier")
    public ResponseEntity<Turnier> createTurnier(@Valid @RequestBody Turnier userBody){
        if (userBody.getId() != null){
            throw new TurnierHasAlreadyIdException("Turnier has already an ID");
        }
        Turnier turnier = turnierRepository.save(userBody);

        String path = "/turnier/{id}";
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(turnier.getId());
        return ResponseEntity.created(uri).body(turnier);
    }

    @DeleteMapping("/turnier/{id}")
    public void deleteTurnier(@PathVariable Integer id){
        Optional<Turnier> turnier = turnierRepository.findById(id);
        if (turnier.isPresent()){
            turnierRepository.delete(turnier.get());
        } else {
            throw new TurnierNotFoundException("Turnier not found with ID: " + id);
        }
    }

    @PutMapping("/turnier")
    public ResponseEntity<Turnier> updateTurnier(@Valid @RequestBody Turnier userBody){
        if (turnierRepository.findById(userBody.getId()).isEmpty()){
            throw new TurnierNotFoundException("Turnier not found. Can't be updated");
        }
        Turnier turnier = turnierRepository.save(userBody);

        String path = "/turnier/{id}";
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(turnier.getId());
        return ResponseEntity.created(uri).body(turnier);
    }

}
