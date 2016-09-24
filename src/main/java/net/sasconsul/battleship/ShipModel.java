package net.sasconsul.battleship;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
public class ShipModel {
    public ShipType shipType;
    public GridPoint topLeft;   // on the playing grid
    public int size; // in grid blocks

    // V=vertical, H=horizontal
    public Orientation orientation;

    public int hits;
    public ShotResult status;

    public ShipModel(final ShipType shipType, final Orientation orientation, final GridPoint topLeft) {
        this.shipType = shipType;
        this.size = shipType.size();
        this.orientation = orientation;
        this.topLeft = topLeft;
        this.status = ShotResult.Taken;
    }

    public ShotResult recordHit(GridPoint gridPoint) {
        hits++;
        ShotResult ans = ShotResult.Hit;
        if (hits >= size) {
            this.status = ShotResult.Sunk;
            ans = ShotResult.Sunk;
        }
        return ans;
    }

    public ShotResult getStatus(GridPoint gridPoint) {
        return this.status;
    }

}
