package at.meks.agileboards.adapters.database.team;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "TEAM")
public class TeamEntity extends PanacheEntityBase {

    @Id
    public String id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    public Collection<TeamEventEntity> events;

}
