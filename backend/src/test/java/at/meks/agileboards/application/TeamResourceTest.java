package at.meks.agileboards.application;

import at.meks.agileboards.domain.core.model.team.TeamName;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.ws.rs.core.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static at.meks.agileboards.TestConstants.UUID_REGEX;

@QuarkusTest
class TeamResourceTest {


    @InjectMock
    TeamRepository teamRepository;

    @Test
    void notExistingTeamIsAdded() {
        RestAssured.given()
                .queryParam("teamName", "My Team")
                .when().post("/team")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .header("location", Matchers.matchesRegex("http://localhost:8081/team/" + UUID_REGEX));
    }

    @Test
    void existingTeamIsAdded() {
        Mockito.when(teamRepository.exists(new TeamName("My Team"))).thenReturn(true);
        RestAssured.given()
                .queryParam("teamName", "My Team")
                .when().post("/team")
                .then()
                .statusCode(Response.Status.CONFLICT.getStatusCode());
    }
}