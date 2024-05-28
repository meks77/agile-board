package at.meks.agileboards.application;

import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.usecases.board.BoardRepository;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.Response.Status.CONFLICT;
import static jakarta.ws.rs.core.Response.Status.PRECONDITION_FAILED;

@QuarkusTest
class BoardResourceTest {

    @InjectMock
    TeamRepository teamRepository;
    @InjectMock
    BoardRepository boardRepository;

    @BeforeEach
    void givenTeamIdExists() {
        Mockito.when(teamRepository.exists(new TeamId("myTeamId"))).thenReturn(true);
    }

    @Test
    void notExistingBoardIsAdded() {
        given()
                .queryParam("teamId", "myTeamId")
                .queryParam("boardName", "My Board")
                .when().post("/board")
                .then()
                .statusCode(200);
    }

    @Test
    void existingBoardIsAdded() {
        Mockito.when(boardRepository.exists(new TeamId("myTeamId"), new BoardName("My Board")))
                .thenReturn(true);
        given()
                .queryParam("teamId", "myTeamId")
                .queryParam("boardName", "My Board")
                .when().post("/board")
                .then()
                .statusCode(CONFLICT.getStatusCode());
    }

    @Test
    void teamIdIsNotExisting() {
        Mockito.when(teamRepository.exists(new TeamId("myTeamId"))).thenReturn(false);
        given()
                .queryParam("teamId", "myTeamId")
                .queryParam("boardName", "My Board")
                .when().post("/board")
                .then()
                .statusCode(PRECONDITION_FAILED.getStatusCode());
    }

}