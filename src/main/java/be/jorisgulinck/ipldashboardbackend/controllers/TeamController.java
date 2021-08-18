package be.jorisgulinck.ipldashboardbackend.controllers;

import be.jorisgulinck.ipldashboardbackend.models.Match;
import be.jorisgulinck.ipldashboardbackend.models.Team;
import be.jorisgulinck.ipldashboardbackend.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName) throws Exception {
        return this.teamService.getTeamByName(teamName);
    }

    @GetMapping("/teams/{teamName}/matches")
    public List<Match> getMatchesOfTeam(@PathVariable String teamName, @RequestParam int year) throws Exception {
        return this.teamService.findMatchesOfTeamByDate(teamName, year);
    }

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return this.teamService.getAllTeams();
    }
}
