package at.meks.agileboards.adapters.database.team;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("TeamCreatedEvent")
@Table(name = "TEAM_CREATED_EVENT")
public class TeamCreatedEventEntity extends TeamEventEntity {

    public String teamName;

}
