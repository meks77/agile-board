package at.meks.agileboards.domain.core.usecases.team;

import at.meks.agileboards.domain.core.model.DomainEntity;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class EqualsHashcodeTest {

    @Test
    void testValueModels() {
        EqualsVerifier.forPackage("at.meks.agileboards.domain.core.model", true)
                .except(aClass -> aClass.isAnnotationPresent(DomainEntity.class))
                .verify();
    }

    @Test
    void testEntities() {
        EqualsVerifier.forPackage("at.meks.agileboards.domain.core.model", true)
                .except(aClass -> !aClass.isAnnotationPresent(DomainEntity.class))
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
}
