package at.meks.agileboards.domain.core.model.team;

import static at.meks.validation.args.ArgValidator.validate;

public record TeamName(String text) {

    public TeamName {
        validate().that(text).withArgumentName("text").isNotBlank();
    }
}
