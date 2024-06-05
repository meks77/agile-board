package at.meks.agileboards.adapters.database.team;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamCreated;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.model.team.TeamName;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestTransaction
class JpaTeamRepositoryTest {

    @Inject
    TeamRepository repo;

    @Test
    void existsWhenteamExists() {
        repo.add(new TeamCreated(TeamId.random(), new TeamName("My Team")));
        boolean result = repo.exists(new TeamName("My Team"));
        assertTrue(result);
    }

    @Test
    void existsWhenTeamDoesNotExist() {
        repo.add(new TeamCreated(TeamId.random(), new TeamName("My Team")));
        boolean result = repo.exists(new TeamName("My Team1"));
        assertFalse(result);
    }

    @Test
    void add() {
        TeamCreated teamCreated = new TeamCreated(TeamId.random(), new TeamName("My Team"));
        repo.add(teamCreated);
        Team result = repo.get(teamCreated.teamId());
        assertEquals(new Team(teamCreated), result);
    }

    @Test
    void getAll() {
        int teamCountBefore = repo.getAll().size();
        repo.add(new TeamCreated(TeamId.random(), new TeamName("My Team1")));
        repo.add(new TeamCreated(TeamId.random(), new TeamName("My Team2")));
        repo.add(new TeamCreated(TeamId.random(), new TeamName("My Team3")));
        repo.add(new TeamCreated(TeamId.random(), new TeamName("My Team4")));

        Collection<Team> result = repo.getAll();

        assertThat(result).hasSize(4 + teamCountBefore);
    }

    @Test
    void get() {
        TeamCreated teamCreated = new TeamCreated(TeamId.random(), new TeamName("My Team"));
        repo.add(teamCreated);
        Team result = repo.get(teamCreated.teamId());
        assertEquals(new Team(teamCreated), result);
    }
}