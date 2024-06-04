package at.meks.agileboards.domain.core.model;

import java.util.UUID;

public record EventId(String uuid) {

    public static EventId random() {
        return new EventId(UUID.randomUUID().toString());
    }
}
