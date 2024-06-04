package at.meks.agileboards.adapters.database.team;

import at.meks.agileboards.domain.core.model.team.TeamId;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public class TeamEventEntity extends PanacheEntityBase {

    @Id
    public String id;

    @ManyToOne
    public TeamEntity team;

    public LocalDateTime created;

    public static List<TeamEventEntity> findByTeamId(TeamId teamId) {
        return TeamEventEntity.list("team.id", teamId.uuid());
    }

}
