package at.meks.agileboards.domain.core.model;

import java.time.LocalDateTime;

public record Timestamp(LocalDateTime localDateTime) {

    public static Timestamp now() {
        return new Timestamp(LocalDateTime.now());
    }

}
