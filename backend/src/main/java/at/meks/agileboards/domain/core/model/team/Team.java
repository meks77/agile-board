package at.meks.agileboards.domain.core.model.team;


import at.meks.agileboards.domain.core.model.DomainEntity;
import at.meks.agileboards.domain.core.usecases.team.TeamCreated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Accessors(chain = false, fluent = true)
@DomainEntity
public final class Team {

    @EqualsAndHashCode.Include
    private final TeamId id;
    private TeamName teamName;

    public Team(TeamCreated teamName) {
        this.id = TeamId.random();
        this.teamName = teamName.teamName();
    }

}
