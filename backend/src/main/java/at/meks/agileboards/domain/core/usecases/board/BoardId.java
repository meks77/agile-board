package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.Id;

import java.util.UUID;

import static at.meks.validation.args.ArgValidator.validate;

public record BoardId(String uuid) {
    public BoardId {
        validate().that(uuid)
                .isNotBlank()
                .matches(s -> s.length() == Id.LENGTH);
    }

    public static BoardId random() {
        return new BoardId(UUID.randomUUID().toString());
    }
}
