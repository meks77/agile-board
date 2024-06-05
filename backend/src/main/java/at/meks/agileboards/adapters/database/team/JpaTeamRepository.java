package at.meks.agileboards.adapters.database.team;

import at.meks.agileboards.domain.core.model.EventId;
import at.meks.agileboards.domain.core.model.Timestamp;
import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamCreated;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.model.team.TeamName;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional(Transactional.TxType.MANDATORY)
public class JpaTeamRepository implements TeamRepository {

    @Override
    public boolean exists(TeamName newTeamName) {
        return getAll().stream().anyMatch(team -> team.teamName().equals(newTeamName));
    }

    @SneakyThrows
    @Override
    public void add(TeamCreated teamCreated) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.id = teamCreated.teamId().uuid();
        var eventEntity = new TeamCreatedEventEntity();
        eventEntity.id = teamCreated.id().uuid();
        eventEntity.team = teamEntity;
        eventEntity.teamName = teamCreated.teamName().text();
        eventEntity.created = teamCreated.created().localDateTime();
        teamEntity.events = List.of(eventEntity);
        teamEntity.persist();
    }

    @Override
    public boolean exists(TeamId existingTeamId) {
        return TeamEntity.findByIdOptional(existingTeamId.uuid()).isPresent();
    }

    @Override
    public Collection<Team> getAll() {
        Stream<TeamEntity> teamStream = TeamEntity.streamAll();
        return teamStream.map(e -> translateToDomain(e.events))
                .collect(Collectors.toSet());
    }

    private Team translateToDomain(Collection<TeamEventEntity> events) {
        return events.stream()
                .filter(e -> e instanceof TeamCreatedEventEntity)
                .findFirst()
                .map(e -> (TeamCreatedEventEntity) e)
                .map(e -> new Team(new TeamCreated(new EventId(e.id), new Timestamp(e.created), new TeamId(e.team.id), new TeamName(e.teamName))))
                .orElseThrow();
    }

    @Override
    public Team get(TeamId teamId) {
        return translateToDomain(TeamEventEntity.findByTeamId(teamId));
    }
}
