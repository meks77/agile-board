package at.meks.agileboards.application.team;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.model.team.TeamName;
import at.meks.agileboards.domain.core.usecases.team.AddTeam;
import at.meks.agileboards.domain.core.usecases.team.TeamAlreadyExistsException;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.stream.Collectors;

@Path("/team")
@Transactional
public class TeamResource {

    @Inject
    AddTeam addTeam;

    @Inject
    TeamRepository teamRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeams() {
        return Response.ok(teamRepository.getAll().stream()
                .map(TeamDto::from)
                .collect(Collectors.toSet())).build();
    }

    @POST
    public Response add(@QueryParam("teamName") String teamName) {
        try {
            Team team = addTeam.addTeam(new TeamName(teamName));
            return Response.created(URI.create("/team/" + team.id().uuid())).build();
        } catch (TeamAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TeamDto getTeam(@PathParam("id") String id) {
        return TeamDto.from(teamRepository.get(new TeamId(id)));
    }
}
