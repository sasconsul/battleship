package net.sasconsul.battleship;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrimaryGrid extends Grid {
    private HashSet<ShipModel> ships;
    // map the grid points of each ship;
    public HashMap<GridPoint, ShipModel> shipPositions = new HashMap<>();

    public PrimaryGrid() {
        super();
        this.ships = new HashSet<>();

        // FIXME hack for testing.
        System.out.println("generate random Fleet Positions");
        this.randomFleetPositions();

        System.out.printf("Fleet of %d shipPositions\n", (new HashSet(shipPositions.values())).size());

    }

    /**
     * Print out the primary grid
     */
    public void printPrimaryGrid() {

        printGridHeader();

        // Grid
        for (int y = 1; y <= Grid.getHeight(); y++) {
            System.out.printf("[%2d]", y);
            for (int x = 1; x <= Grid.getWidth(); x++) {
                GridPoint gridPoint = new GridPoint(x, y);
                ShipModel ship = this.shipPositions.get(gridPoint);
                plotGrid(ship);
            }
            System.out.println();
        }
    }

    private void plotGrid(ShipModel ship) {
        if (ship == null) {
            System.out.print("__");
        } else {
            System.out.printf("%s%s", ship.shipType.name().substring(0, 1),
                    ship.getStatus().name().substring(0,1).toLowerCase());
        }
    }


    /**
     * Find out if the shot hit a target in the fleet.
     *
     * @param gridPoint beign sot at.
     * @return the result of the shot.
     */
    public ShotResult findTarget(GridPoint gridPoint) {

        // Look into the Hash map for a hit, which means that shot hit a ship.
        ShipModel theShip = shipPositions.get(gridPoint);
        ShotResult shotResult = (theShip == null) ? ShotResult.Miss : ShotResult.Hit;
        if (shotResult == ShotResult.Hit) {
            shotResult = theShip.recordHit(gridPoint);
        }

        //System.out.printf("findTarget: shot at %s is a %s \n", gridPoint.toString(), shotResult.name());
        return shotResult;
    }

    public int countWorkingShips() {
        int working = 0;
        for (ShipModel shipModel : ships) {
            if (shipModel.status != ShotResult.Sunk) {
                working++;
            }
        }

        return working;
    }

    //
    // FIXME hack for testing.
    //

    // Hack to generate a points for testing engine
    //
    private static Random rand = new Random();

    private GridPoint randomPoint() {
        // Generate on the 1..max grid
        return new GridPoint(
                rand.nextInt(Grid.getWidth() - 1) + 1,
                rand.nextInt(Grid.getHeight() - 1) + 1
        );

    }
    private void randomFleetPositions() {

        ShipType[] values = ShipType.values();
        for (ShipType shipType : values) {

            for (int count = 1; count <= shipType.num(); count++) {
                boolean placeShip;
                do {
                    //FIXME what about shipPositions that cannot be placed on the current Grid?
                    //  Meaning the grid is too full.
                    //
                    Orientation orientation = Orientation.randOrientation();
                    GridPoint topLeft = randomPoint();
                    ShipModel ship = new ShipModel(shipType, orientation, topLeft);

                    //
                    //  Try placing on grid w/o hitting another ship.
                    //
                    placeShip = true; // reset for switch logic
                    HashMap<GridPoint, ShipModel> shipPoints = new HashMap<>();
                    switch (orientation) {
                        case V: {
                            for (int y = topLeft.getY(); y < (topLeft.getY() + shipType.size()); y++) {
                                GridPoint testPoint = new GridPoint(topLeft.getX(), y);
                                if (this.shipPositions.get(testPoint) != null) {
                                    placeShip = false;
                                    break;
                                }
                                // OK to place a ship
                                shipPoints.put(testPoint, ship);
                                System.out.printf("randomFleetPositions: ship:%s at %s\n",
                                        shipType.toString(), testPoint.toString());
                            }
                            break;
                        }
                        case H: {
                            for (int x = topLeft.getX(); x < (topLeft.getX() + shipType.size()); x++) {
                                GridPoint testPoint = new GridPoint(x, topLeft.getY());
                                if (this.shipPositions.get(testPoint) != null) {
                                    placeShip = false;
                                    break;
                                }
                                shipPoints.put(testPoint, ship);
                                System.out.printf("randomFleetPositions: ship:%s at %s\n",
                                        shipType.toString(), testPoint.toString());
                            }
                            break;
                        }
                    }

                    if (placeShip) {
                        if (ships.add(ship) ) {
                            shipPositions.putAll(shipPoints);
                        } else {
                            System.out.printf("randomFleetPositions: duplicate ship %s\n", ship);
                        }
                    }
                } while (!placeShip);
            }
        }
    }
}

