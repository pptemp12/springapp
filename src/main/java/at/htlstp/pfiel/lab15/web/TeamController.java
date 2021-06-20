package at.htlstp.pfiel.lab15.web;

import at.htlstp.pfiel.lab15.error.*;
import at.htlstp.pfiel.lab15.model.Spieler;
import at.htlstp.pfiel.lab15.model.Team;
import at.htlstp.pfiel.lab15.model.Turnier;
import at.htlstp.pfiel.lab15.repo.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    @GetMapping("/team/{kuerzel}")
    public Team getTeamByKuerzel(@PathVariable String kuerzel){
        return teamRepository.findById(kuerzel).orElseThrow(() -> new TeamNotFoundException("Team not found with Kuerzel: " + kuerzel));
    }

    @GetMapping("/team/name/{name}")
    public List<Team> getTeamsByNameContains(@PathVariable String name){
        return teamRepository.findTeamByNameContains(name);
    }

    @GetMapping("/team/turnier/{id}")
    public List<Team> getTeamsByTurnierId(@PathVariable Integer id){
        List<Team> teams = teamRepository.findTeamsByTurnier(id);
        if (teams.isEmpty()){
            throw new TurnierNotFoundException("Turnier not found with Id: " + id);
        }
        return teams;
    }

    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@Valid @RequestBody Team userBody){
        if (teamRepository.findById(userBody.getKuerzel()).isPresent()){
            throw new TeamAlreadyExistsException("Team already exists");
        }
        Team team = teamRepository.save(userBody);

        String path = "/team/{kuerzel}";
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(team.getKuerzel());
        return ResponseEntity.created(uri).body(team);
    }

    @DeleteMapping("/team/{kuerzel}")
    public void deleteTeam(@PathVariable String kuerzel){
        Optional<Team> team = teamRepository.findById(kuerzel);
        if (team.isPresent()){
            teamRepository.delete(team.get());
        } else {
            throw new SpielerNotFoundException("Team not found with Kuerzel: " + kuerzel);
        }
    }

    @PutMapping("/team")
    public ResponseEntity<Team> updateTeam(@Valid @RequestBody Team userBody){
        if (teamRepository.findById(userBody.getKuerzel()).isEmpty()){
            throw new TeamNotFoundException("Team not found. Can't be updated");
        }
        Team team = teamRepository.save(userBody);

        String path = "/team/{kuezel}";
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(team.getKuerzel());
        return ResponseEntity.created(uri).body(team);
    }


}
