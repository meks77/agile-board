package at.meks.agileboards.application;

import at.meks.agileboards.domain.core.model.board.Board;
import at.meks.agileboards.domain.core.model.board.BoardId;
import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.usecases.board.AddBoard;
import at.meks.agileboards.domain.core.usecases.board.BoardAlreadyExists;
import at.meks.agileboards.domain.core.usecases.board.BoardRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.Collection;

@Path(BoardResource.BOARD_ROOT_URL)
public class BoardResource {

    public static final String BOARD_ROOT_URL = "/board";
    @Inject
    BoardRepository boardRepository;

    @Inject
    AddBoard addBoard;

    @GET
    public Collection<Board> getBoards() {
        return boardRepository.getAll();
    }

    @GET
    @Path("{id}")
    public Board getBoard(@PathParam("id") String id) {
        return boardRepository.get(new BoardId(id)).orElse(null);
    }

    @POST
    public Response createBoard(@QueryParam("teamId") String teamId, @QueryParam("boardName") String boardName) {
        try {
            Board board = addBoard.add(new TeamId(teamId), new BoardName(boardName));
            return Response.created(URI.create(
                            "%s/%s".formatted(BOARD_ROOT_URL, board.id().uuid())))
                    .build();
        } catch (BoardAlreadyExists e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
        }
    }

}
