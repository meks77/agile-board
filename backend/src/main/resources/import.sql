insert into team (id)
values ('b358b671-38af-47f0-948d-6fcae84c04f1');
insert into team (id)
values ('6b3bedbe-9559-4f4c-aa74-6609bee938a9');
insert into team (id)
values ('51e12d82-f9d1-443b-ab4a-9628f5ad3faa');

insert into team_created_event (id, team_id, created, teamname)
values ('60074fe7-d2fa-4cd1-a7c3-7d9079435abb', 'b358b671-38af-47f0-948d-6fcae84c04f1', now(), 'Team 1');
insert into team_created_event (id, team_id, created, teamname)
values ('ded96d07-a479-4c5c-9a19-dd72b01a68c0', '6b3bedbe-9559-4f4c-aa74-6609bee938a9', now(), 'Team 2');
insert into team_created_event (id, team_id, created, teamname)
values ('ff22494e-d5d9-421a-80c3-cf5afc94efaf', '51e12d82-f9d1-443b-ab4a-9628f5ad3faa', now(), 'Team 3');