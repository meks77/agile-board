package at.meks.agileboards.application;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamCreated;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.model.team.TeamName;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.ws.rs.core.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.nio.file.Path;

import static at.meks.agileboards.TestConstants.UUID_REGEX;

@QuarkusTest
class TeamResourceTest {

    @InjectMock
    TeamRepository teamRepository;

    @Test
    void notExistingTeamIsAdded() {
        String locationNewTeam = RestAssured.given()
                .queryParam("teamName", "My Team")
                .when().post("/team")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .header("location", Matchers.matchesRegex("http://localhost:8081/team/" + UUID_REGEX))
                .extract().header("location");

        TeamId teamId = new TeamId(Path.of(URI.create(locationNewTeam).getPath()).getName(1).toString());
        Mockito.when(teamRepository.get(teamId))
                .thenReturn(new Team(new TeamCreated(teamId, new TeamName("My Team"))));
        RestAssured.given()
                .when()
                .get(locationNewTeam)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("name", Matchers.equalTo("My Team"))
                .and().body("id", Matchers.equalTo(teamId.uuid()));
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