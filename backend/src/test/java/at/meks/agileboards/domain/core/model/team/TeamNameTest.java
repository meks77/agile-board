package at.meks.agileboards.domain.core.model.team;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class TeamNameTest {

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "\r", "\t"})
    @NullAndEmptySource
    void nameIsNullOrEmpty(String text) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new TeamName(text))
                .withMessageContaining("text");
    }

}