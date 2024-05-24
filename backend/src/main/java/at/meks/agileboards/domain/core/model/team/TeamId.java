package at.meks.agileboards.domain.core.model.team;

import java.util.UUID;

public record TeamId(String uuid) {
    public static TeamId random() {
        return new TeamId(UUID.randomUUID().toString());
    }
}
