CREATE TABLE IF NOT EXISTS TROOP(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    current_health INTEGER NOT NULL,
    max_health INTEGER NOT NULL,
    pos_x DOUBLE NOT NULL,
    pos_y DOUBLE NOT NULL,
    normal_speed DOUBLE NOT NULL,
    street_speed DOUBLE NOT NULL,
    difficult_terrain_speed DOUBLE NOT NULL,
    close_combat_range DOUBLE NOT NULL,
    ranged_combat_range DOUBLE NOT NULL,
    normal_view_distance DOUBLE NOT NULL,
    disadvantaged_view_distance DOUBLE NOT NULL,
    advantaged_view_distance DOUBLE NOT NULL
)

CREATE TABLE IF NOT EXISTS ARMY(
    id INTEGER,
    hq INTEGER,
    troop INTEGER,
    FOREIGN KEY(hq) REFERENCES TROOP(id),
    FOREIGN KEY(troop) REFERENCES TROOP(id),
    UNIQUE(hq, troop),
    PRIMARY KEY (id, hq, troop)
)