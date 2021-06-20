INSERT INTO turnier(turnier_name, turnier_ort, turnier_date) VALUES ('LJ 2020 Beachen', 'Sitzenberg-Reidling', '2020-08-08')
INSERT INTO turnier(turnier_name, turnier_ort, turnier_date) VALUES ('LJ 2021 Beachen', 'Sitzenberg-Reidling', '2021-08-08')

INSERT INTO team (team_kuerzel, team_name) VALUES ('SKLT', 'SK LATION')
INSERT INTO team (team_kuerzel, team_name) VALUES ('LOST', 'Die Verlorenen')
INSERT INTO team (team_kuerzel, team_name) VALUES ('WINI', 'Die Gewinner')
INSERT INTO team (team_kuerzel, team_name) VALUES ('ERGA', 'Die Aergsten')

INSERT INTO spieler(spieler_nachname, spieler_vorname, spieler_geburtsdatum, spieler_team) VALUES ('Pfeil', 'Paul', '2003-01-10', 'ERGA')
INSERT INTO spieler(spieler_nachname, spieler_vorname, spieler_geburtsdatum, spieler_team) VALUES ('Ergaferga', 'Karin', '1999-01-25', 'ERGA')
INSERT INTO spieler(spieler_nachname, spieler_vorname, spieler_geburtsdatum, spieler_team) VALUES ('Ergaferga', 'Michi', '1995-12-27', 'ERGA')
INSERT INTO spieler(spieler_nachname, spieler_vorname, spieler_geburtsdatum, spieler_team) VALUES ('Mayer', 'Peter', '2001-02-11', 'ERGA')
INSERT INTO spieler(spieler_nachname, spieler_vorname, spieler_geburtsdatum, spieler_team) VALUES ('Hacker', 'Michael', '1995-01-10', 'SKLT')
INSERT INTO spieler(spieler_nachname, spieler_vorname, spieler_geburtsdatum, spieler_team) VALUES ('Oellerer', 'Joe', '1992-05-17', 'SKLT')
INSERT INTO spieler(spieler_nachname, spieler_vorname, spieler_geburtsdatum, spieler_team) VALUES ('Wein', 'Livi', '1993-10-15', 'SKLT')
INSERT INTO spieler(spieler_nachname, spieler_vorname, spieler_geburtsdatum, spieler_team) VALUES ('Hofer', 'Tom', '1994-02-07', 'SKLT')



INSERT INTO turnier_team(turnier_id, team_kuerzel) VALUES (1, 'SKLT')
INSERT INTO turnier_team(turnier_id, team_kuerzel) VALUES (1, 'ERGA')
INSERT INTO turnier_team(turnier_id, team_kuerzel) VALUES (1, 'WINI')
INSERT INTO turnier_team(turnier_id, team_kuerzel) VALUES (2, 'ERGA')
INSERT INTO turnier_team(turnier_id, team_kuerzel) VALUES (2, 'LOST')
