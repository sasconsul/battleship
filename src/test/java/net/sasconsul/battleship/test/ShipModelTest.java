package net.sasconsul.battleship.test;

import net.sasconsul.battleship.GridPoint;
import net.sasconsul.battleship.Orientation;
import net.sasconsul.battleship.ShipModel;
import net.sasconsul.battleship.ShipType;
import org.junit.Test;

/**
 * Created by sasconsul on 8/25/16.
 * Copyright Stuart Schmukler 2016
 *
 */
public class ShipModelTest {

    @Test
    public void isHitTest() throws Exception {

        GridPoint lGridPoint = new GridPoint(2,2);
        ShipModel ship = new ShipModel(ShipType.BattleShip, Orientation.V, lGridPoint);

        ship.recordHit(new GridPoint(2,2));
    }


}