package at.meks.agileboards.domain.core.usecases.team;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamName;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddTeamTest {

    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    AddTeam addTeam;

    @SneakyThrows
    @Test
    void teamDoesNotExist() {
        TeamName teamName = new TeamName("testName");

        Team team = addTeam.addTeam(teamName);

        assertThat(team.teamName())
                .isEqualTo(teamName);
        assertThat(team.id()).isNotNull();
    }

    @SneakyThrows
    @Test
    void teamIsSavedWhenAdded() {
        TeamName teamName = new TeamName("testName");
        addTeam.addTeam(teamName);

        ArgumentCaptor<TeamCreated> captor = ArgumentCaptor.forClass(TeamCreated.class);
        verify(teamRepository).add(captor.capture());
        assertThat(captor.getValue().teamName()).isEqualTo(teamName);
        assertThat(captor.getValue().teamId()).isNotNull();
    }

    @Test
    void teamAlreadyExists() {
        TeamName teamName = new TeamName("testName");
        given(teamRepository.exists(teamName)).willReturn(true);

        assertThatExceptionOfType(TeamAlreadyExistsException.class)
                .isThrownBy(() -> addTeam.addTeam(teamName));
    }

}