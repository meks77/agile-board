package at.meks.agileboards.domain.core.model.board;

import at.meks.agileboards.domain.core.model.DomainEntity;
import at.meks.agileboards.domain.core.model.team.TeamId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import static at.meks.validation.args.ArgValidator.validate;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DomainEntity
public final class Board {

    @EqualsAndHashCode.Include
    private final BoardId id;
    private final TeamId teamId;
    private final BoardName name;

    public Board(BoardCreated boardCreated) {
        validate().that(boardCreated).isNotNull();
        id = boardCreated.id();
        teamId = boardCreated.teamId();
        name = boardCreated.name();
    }

}
