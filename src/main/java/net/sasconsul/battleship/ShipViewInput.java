package net.sasconsul.battleship;

import lombok.Data;

@Data
public class ShipViewInput {
    public GridPoint center;    // the mid point of the ship
    public int size;            // in grid blocks
    public Orientation orientation;
}
