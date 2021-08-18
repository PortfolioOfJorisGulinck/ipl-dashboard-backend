package be.jorisgulinck.ipldashboardbackend.services;

import be.jorisgulinck.ipldashboardbackend.models.Match;
import be.jorisgulinck.ipldashboardbackend.models.Team;
import be.jorisgulinck.ipldashboardbackend.repo.MatchRepo;
import be.jorisgulinck.ipldashboardbackend.repo.TeamRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepo teamRepo;
    private final MatchRepo matchRepo;

    public Team getTeamByName(String teamName) throws Exception {
        Team team = this.teamRepo.findByTeamName(teamName)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        team.setMatches(findLatestMatchesByTeam(teamName, 4));
        return team;
    }

    public List<Match> findLatestMatchesByTeam(String teamName, int count) {
        Pageable pageable = PageRequest.of(0, count);
        return this.matchRepo.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
    }

    public List<Match> findMatchesOfTeamByDate(String teamName, int year) {
        LocalDate startDate = LocalDate.of(year, 1,1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchRepo.getMatchesByTeamBetweenDates(
                teamName, startDate, endDate);
    }

    public Iterable<Team> getAllTeams() {
        return this.teamRepo.findAll();
    }


}
