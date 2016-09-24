package net.sasconsul.battleship;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = false)
public class TrackingGrid extends Grid {
    private HashMap<GridPoint, Shot> shots = new HashMap();

    public TrackingGrid() {
        super();
    }

    public void updateTrackingGrid(GridPoint position, ShotResult shotResult) {
        Shot theShot = new Shot();
        theShot.pos = position;
        theShot.shotResult = shotResult;
        this.shots.put(position, theShot);
    }

    public Shot wasShotAt(GridPoint position) {
        return shots.get(position);
    }

    public void printTrackingGrid() {

        printGridHeader();

        // Grid
        for (int y = 1; y <= Grid.getHeight(); y++) {
            System.out.printf("[%2d]", y);
            for (int x = 1; x <= Grid.getWidth(); x++) {
                GridPoint gridPoint = new GridPoint(x, y);
                Shot theShot = shots.get(gridPoint);
                if (theShot != null) {
                    ShotResult shotResult = theShot.shotResult;
                    String resultStr = (shotResult == ShotResult.Miss)? ".": shotResult.name().substring(0, 1).toLowerCase();
                    System.out.print("_"+ resultStr);
                } else {
                    System.out.print("__");
                }
            }
            System.out.println();
        }
    }

}