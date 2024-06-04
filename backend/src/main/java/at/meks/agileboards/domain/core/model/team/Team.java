package at.meks.agileboards.domain.core.model.team;


import at.meks.agileboards.domain.core.model.DomainEntity;
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
    private final TeamName teamName;

    public Team(TeamCreated teamCreated) {
        this.id = teamCreated.teamId();
        this.teamName = teamCreated.teamName();
    }

}
