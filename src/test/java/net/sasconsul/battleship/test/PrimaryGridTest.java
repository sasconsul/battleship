package net.sasconsul.battleship.test;

import net.sasconsul.battleship.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by sasconsul on 8/25/16.
 * Copyright Stuart Schmukler 2016
 *
 */
public class PrimaryGridTest {

    @Test
    public void findTarget() throws Exception {

    }

    @Test
    public void randomFleetCount() throws Exception {
        PrimaryGrid lPrimaryGrid = new PrimaryGrid();
        assertEquals(ShipType.values().length, lPrimaryGrid.getShips().size());

    }
    @Test
    public void workingShips() throws Exception {
        PrimaryGrid lPrimaryGrid = new PrimaryGrid();

        assertEquals(ShipType.values().length, lPrimaryGrid.countWorkingShips());

        HashSet<ShipModel> lShips = lPrimaryGrid.getShips();
        for (ShipModel aShip : lShips) {
            aShip.setStatus(ShotResult.Sunk);
        }

        assertEquals(0, lPrimaryGrid.countWorkingShips());
    }

    public static final Set<ShotResult> SHOT_HIT_OR_SUNK = Collections.unmodifiableSet(new HashSet<ShotResult>(Arrays.asList(
            new ShotResult[]{ShotResult.Hit, ShotResult.Sunk}
    )));

    @Test
    public void shootingTest1() {
        PrimaryGrid lPrimaryGrid = new PrimaryGrid();

        System.out.println("grid before shooting all ships.");
        lPrimaryGrid.printPrimaryGrid();

        // Get the set of ship positions.
        HashMap<GridPoint, ShipModel> ships = lPrimaryGrid.getShipPositions();

        // For all of the ship positions.
        for(Map.Entry<GridPoint, ShipModel> e : ships.entrySet()) {

            //Shoot at each position
            GridPoint lPos = e.getKey();
            ShotResult lShotResult = lPrimaryGrid.findTarget(lPos);
            assertTrue("For "+lPos.toString()+" lShotResult="+lShotResult.name()+" should be Hit or Sunk. ",
                    SHOT_HIT_OR_SUNK.contains(lShotResult));
        }

        System.out.println("\nGrid after shooting all ships.");
        lPrimaryGrid.printPrimaryGrid();

    }
}