package be.jorisgulinck.ipldashboardbackend.repo;

import be.jorisgulinck.ipldashboardbackend.models.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo extends CrudRepository<Team, Long> {
    Optional<Team> findByTeamName(String teamName);
}
