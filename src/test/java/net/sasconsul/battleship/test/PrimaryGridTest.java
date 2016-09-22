package net.sasconsul.battleship;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by sasconsul on 8/25/16.
 */
public class PrimaryGridTest {
    @Test
    public void findTarget() throws Exception {

    }

    @Test
    public void workingShips() throws Exception {
        PrimaryGrid lPrimaryGrid = new PrimaryGrid();
        assertEquals(0, lPrimaryGrid.workingShips());

        HashMap<GridPoint, ShipModel> ships =new HashMap<>();
        ShipModel lShipModel = new ShipModel();
        GridPoint lGridPoint = new GridPoint();
        lGridPoint.setX(5);
        lGridPoint.setY(5);

        ships.put(lGridPoint,lShipModel);

        lPrimaryGrid.setShips(ships);
        assertEquals(1, lPrimaryGrid.workingShips());
    }

}