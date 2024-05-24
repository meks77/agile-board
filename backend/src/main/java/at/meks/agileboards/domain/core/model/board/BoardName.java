package at.meks.agileboards.domain.core.model.board;

import static at.meks.validation.args.ArgValidator.validate;

public record BoardName(String text) {

    public BoardName {
        validate().that(text).withArgumentName("text").isNotBlank();
    }
}
