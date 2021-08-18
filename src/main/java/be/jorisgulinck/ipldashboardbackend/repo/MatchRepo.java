package be.jorisgulinck.ipldashboardbackend.repo;

import be.jorisgulinck.ipldashboardbackend.models.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepo extends CrudRepository<Match, Long> {
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) " +
            "and m.date between :startDate and :endDate order by m.date desc")
    List<Match> getMatchesByTeamBetweenDates(
            @Param("teamName") String teamName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    // List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    //        String teamName1, LocalDate startDateT1, LocalDate endDateT1, String teamName2, LocalDate startDateT2, LocalDate endDateT2);
}
