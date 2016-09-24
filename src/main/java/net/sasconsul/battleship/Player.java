package net.sasconsul.battleship;

import lombok.Data;

import java.util.List;

@Data
public class Player {
    public PrimaryGrid primaryGrid;
    public TrackingGrid trackingGrid;
    int playerId;
    public Player(int playerId) {
        this.playerId = playerId;

        System.out.printf("\nPlayer%d ", playerId);
        primaryGrid = new PrimaryGrid();
        trackingGrid = new TrackingGrid();
    }

    public void updateTrackingGrid(GridPoint position, ShotResult shotResult) {
        this.trackingGrid.updateTrackingGrid(position, shotResult);
    }

    public void printGrids() {
        System.out.printf("printGrids: Primary Grid:\n");
        primaryGrid.printPrimaryGrid();

        System.out.println();
        System.out.printf("printGrids: Tracking Grid:\n");
        trackingGrid.printTrackingGrid();
    }

}
