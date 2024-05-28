package at.meks.agileboards.application;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamName;
import at.meks.agileboards.domain.core.usecases.team.AddTeam;
import at.meks.agileboards.domain.core.usecases.team.TeamAlreadyExistsException;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/team")
public class TeamResource {

    @Inject
    AddTeam addTeam;

    @POST
    public Response add(@QueryParam("teamName") String teamName) {
        try {
            Team team = addTeam.addTeam(new TeamName(teamName));
            return Response.created(URI.create("/team/" + team.id().uuid())).build();
        } catch (TeamAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
