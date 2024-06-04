package at.meks.agileboards.application.team;

import at.meks.agileboards.domain.core.model.team.Team;
import lombok.Data;

@Data
public class TeamDto {

    private String id;
    private String name;

    public static TeamDto from(Team team) {
        TeamDto dto = new TeamDto();
        dto.setId(team.id().uuid());
        dto.setName(team.teamName().text());
        return dto;
    }

}
