package net.sasconsul.battleship;

import lombok.Data;

@Data
public class Shot {
    public GridPoint pos;
    public ShotResult shotResult;
    int player;
}