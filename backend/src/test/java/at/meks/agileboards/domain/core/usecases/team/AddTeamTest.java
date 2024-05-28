package at.meks.agileboards.domain.core.usecases.team;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamCreated;
import at.meks.agileboards.domain.core.model.team.TeamName;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddTeamTest {

    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    AddTeam addTeam;

    @SneakyThrows
    @Test
    void teamNotExisting() {
        TeamName teamName = new TeamName("testName");

        Team team = addTeam.addTeam(teamName);

        assertNotNull(team);
        assertNotNull(team.id());
        assertEquals(teamName, team.teamName());
    }

    @SneakyThrows
    @Test
    void teamIsPersistedWhenAdded() {
        TeamName teamName = new TeamName("testName");
        addTeam.addTeam(teamName);

        ArgumentCaptor<TeamCreated> captor = ArgumentCaptor.forClass(TeamCreated.class);
        verify(teamRepository).add(captor.capture());
        var teamCreated = captor.getValue();
        assertNotNull(teamCreated);
        assertThat(teamCreated.teamName()).isEqualTo(teamName);
        assertThat(teamCreated.teamId()).isNotNull();
    }

    @Test
    void teamAlreadyExists() {
        TeamName teamName = new TeamName("testName");
        given(teamRepository.exists(teamName)).willReturn(true);

        assertThatExceptionOfType(TeamAlreadyExistsException.class)
                .isThrownBy(() -> addTeam.addTeam(teamName));
        verify(teamRepository, never()).add(any());
    }

    @Test
    void teamNameIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> addTeam.addTeam(null))
                .withMessageContaining("teamName");
    }

}