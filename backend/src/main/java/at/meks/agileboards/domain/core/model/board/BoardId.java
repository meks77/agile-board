package at.meks.agileboards.domain.core.model.board;

import java.util.UUID;

import static at.meks.validation.args.ArgValidator.validate;

public record BoardId(String uuid) {
    public BoardId {
        validate().that(uuid)
                .isNotBlank()
                .hasMinLength(1);
    }

    public static BoardId random() {
        return new BoardId(UUID.randomUUID().toString());
    }
}
