package at.htlstp.pfiel.lab15.web;

import at.htlstp.pfiel.lab15.error.*;
import at.htlstp.pfiel.lab15.model.Spieler;
import at.htlstp.pfiel.lab15.model.Turnier;
import at.htlstp.pfiel.lab15.repo.SpielerRepository;
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
public class SpielerController {
    private final SpielerRepository spielerRepository;

    public SpielerController(SpielerRepository spielerRepository) {
        this.spielerRepository = spielerRepository;
    }

    @GetMapping("/spieler")
    public List<Spieler> getAllSpieler() {
        return spielerRepository.findAll();
    }

    @GetMapping("/spieler/{id}")
    public Spieler getSpielerById(@PathVariable Integer id) {
        return spielerRepository.findById(id)
                .orElseThrow(() -> new SpielerNotFoundException("Spieler not found with ID: " + id));
    }

    @GetMapping("/spieler/{nachname}/{vorname}")
    public Spieler getSpielerById(@PathVariable String nachname, @PathVariable String vorname) {
        return spielerRepository.findSpielerByNachnameAndVorname(nachname, vorname)
                .orElseThrow(() -> new SpielerNotFoundException("Spieler not found with Nachname=\"" + nachname + "\" and Vorname=\"" + vorname + "\""));
    }

    @GetMapping("/spieler/geburtstag")
    public List<Spieler> getSpielerByGeburtstagBetween(@RequestParam(name = "begin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin, @RequestParam(name = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        if (begin == null && end == null) {
            return spielerRepository.findAll();
        }
        if (begin == null) {
            return spielerRepository.findSpielerByGeburtsdatumBefore(end);
        }
        if (end == null) {
            return spielerRepository.findSpielerByGeburtsdatumAfter(begin);
        }
        if (begin.isAfter(end)) {
            throw new InvalidDateException("Begin-Date can't be before end-date!");
        }
        return spielerRepository.findSpielerByGeburtsdatumBetween(begin, end);
    }

    @GetMapping("/spieler/team/{kuerzel}")
    public List<Spieler> getSpielerByTeam(@PathVariable String kuerzel) {
        List<Spieler> spielerList = spielerRepository.findSpielerByTeam_Kuerzel(kuerzel);
        if (spielerList.isEmpty()){
            throw new  TeamNotFoundException("Team does not exist with Kuerzel: " + kuerzel);
        }
        return spielerList;
    }

    @PostMapping("/spieler")
    public ResponseEntity<Spieler> createSpieler(@Valid @RequestBody Spieler userBody){
        if (userBody.getId() != null){
            throw new SpielerHasAlreadyIdException("Spieler has already an ID");
        }
        Spieler spieler = spielerRepository.save(userBody);

        String path = "/spieler/{id}";
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(spieler.getId());
        return ResponseEntity.created(uri).body(spieler);
    }

    @DeleteMapping("/spieler/{id}")
    public void deleteSpieler(@PathVariable Integer id){
        Optional<Spieler> spieler = spielerRepository.findById(id);
        if (spieler.isPresent()){
            spielerRepository.delete(spieler.get());
        } else {
            throw new SpielerNotFoundException("Spieler not found with ID: " + id);
        }
    }

    @PutMapping("/spieler")
    public ResponseEntity<Spieler> updateSpieler(@Valid @RequestBody Spieler userBody){
        if (spielerRepository.findById(userBody.getId()).isEmpty()){
            throw new SpielerNotFoundException("Spielr not found. Can't be updated");
        }
        Spieler spieler = spielerRepository.save(userBody);

        String path = "/turnier/{id}";
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(spieler.getId());
        return ResponseEntity.created(uri).body(spieler);
    }

}
