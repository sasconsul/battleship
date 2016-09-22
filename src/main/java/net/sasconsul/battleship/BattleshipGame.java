package net.sasconsul.battleship;

/**
 * Created by sasconsul on 8/23/16.
 *
 * JDL-studio link https://jhipster.github.io/jdl-studio/#/view/entity%20GridPoint%20%7B%0A%09x%20Integer%0A%20%20%20%20y%20Integer%0A%7D%0A%0Aenum%20Orientation%20%7B%20V%2C%20H%20%7D%0A%0Aenum%20ShotResult%20%7B%20HIT%2C%20MISS%2C%20TAKEN%2C%20SUNK%2C%20WIN%20%7D%0A%0Aenum%20ShipType%20%7B%0A%20%20%20%20%20%20%20%20AIR_CARRIER%2C%0A%20%20%20%20%20%20%20%20BATTLESHIP%2C%0A%20%20%20%20%20%20%20%20SUBMARINE%2C%0A%20%20%20%20%20%20%20%20CRUISER%2C%0A%20%20%20%20%20%20%20%20PATROL%0A%7D%0A%20%20%20%20%20%20%20%20%0Aentity%20PrimaryGrid%20%7B%0A%09height%20Integer%0A%20%20%20%20width%20Integer%0A%7D%0A%0Aentity%20TrackingGrid%20%7B%0A%09height%20Integer%0A%20%20%20%20width%20Integer%0A%7D%0A%0Aentity%20ShipModel%20%7B%0A%09shipType%20ShipType%20%0A%20%20%20%20topLeft%20GridPoint%20%0A%20%20%20%20orientation%20Orientation%0A%20%20%20%20size%20Integer%0A%20%20%20%20hits%20Integer%0A%20%20%20%20status%20ShotResult%20%0A%7D%0A%0Aentity%20Player%20%7B%0A%09primaryGrid%20PrimaryGrid%0A%20%20%20%20trackingGrid%20TrackingGrid%0A%7D%0Aentity%20Shot%20%7B%0A%09position%20GridPoint%0A%20%20%20%20shot%20ShotResult%0A%7D%0A%0Aentity%20BattleshipGame%20%7B%0A%09turns%20Integer%0A%20%20%20%20status%20ShotResult%0A%7D%0A%0Arelationship%20OneToMany%20%7B%0A%09%20%20PrimaryGrid%7Bships%7D%20to%20ShipModel%7BprimaryGrid%7D%0A%7D%0A%0Arelationship%20OneToMany%7B%0A%09ShipModel%7Bpoints%7D%20to%20GridPoint%7Bship%7D%0A%7D%0A%0Arelationship%20OneToMany%20%7B%0A%09TrackingGrid%7Bshots%7D%20to%20Shot%7BtrackingingGrid%7D%0A%7D%0A%0Arelationship%20OneToOne%20%7B%0A%09Player%7BprimaryGrid%7D%20to%20PrimaryGrid%7Bplayer%7D%0A%7D%0A%0Arelationship%20OneToOne%20%7B%0A%09ShipModel%7Borientation%7D%20to%20Orientation%0A%7D%0A%0Arelationship%20OneToOne%20%7B%0A%09Player%7BtrackingGrid%7D%20to%20TrackingGrid%7Bplayer%7D%0A%7D%0A%0Arelationship%20OneToMany%20%7B%0A%09BattleshipGame%7Bplayer%7D%20to%20Player%7BbattleshipGame%7D%0A%7D%0A%0A%0A%0A
 *
 */

import lombok.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

@Data
public class BattleshipGame {
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


    public Shot shoot(Player[] players, int turn, GridPoint position) {

        // Take turns shooting
        int shooter = turn % players.length;
        Player theShooter = players[shooter];

        // FIXME if there are more that two (2) players. who to shoot at?
        int target = (turn + 1) % players.length;
        Player theTarget = players[target];

        //
        // Evaluate shot position result
        //
        ShotResult shotResult = theTarget.primaryGrid.findTarget(position);
        int workingShips = theTarget.primaryGrid.workingShips();

        if (workingShips == 0) {
            shotResult = ShotResult.Win;
        }

        theShooter.updateTrackingGrid(position, shotResult);

        System.out.printf("[%d] Player%d shot at %s, result a %s\n",
                turn, shooter, position.toString(), shotResult.name());

        theShot.pos = position;
        theShot.shotResult = shotResult;
        theShot.player = shooter;

        return theShot;
    }

    // init the game players
    Player player0 = new Player(0);
    Player player1 = new Player(1);
    Player[] players = {player0, player1};
    int turn = 0;

    // Init the shot answer
    Shot theShot = new Shot();

    public static void main(String[] args) {

        System.out.printf("Tests...\n");

        System.out.printf("\nGame: start the Battleship\n");

        BattleshipGame game = new BattleshipGame();

        game.theShot.shotResult = ShotResult.Taken;

        while (game.theShot.shotResult != ShotResult.Win) {

            // FIXME Hack: Player takes a random shoot
            GridPoint shot = game.randomPoint();

            game.theShot = game.shoot(game.players, game.turn, shot);

            game.turn++;
        }

        // Dump the grids for visual checking
        //
        System.out.println();
        for (int idx = 0; idx < game.players.length; idx++) {
            System.out.printf("Game: player %d grids:\n", idx);
            game.players[idx].printGrids();
            System.out.println();
        }

        System.out.println("Check the winner's tracking grid with the loser's primary grid");
        game.validateWinnerGrid(game.players, game.theShot);


    }


    public void validateWinnerGrid(Player[] players, Shot winningShot) {

        // Take turns shooting
        int shooter = turn % players.length;
        Player theShooter = players[shooter];

        // FIXME if there are more that two (2) players. who to shoot at?
        int target = (turn + 1) % players.length;
        Player theTarget = players[target];

        //Header
        System.out.printf("    ");
        for (int x = 0; x < Grid.getWidth(); x++) {
            System.out.printf("%c", (char) ('a' + x));
        }
        System.out.println();

        // Grid
        for (int y = 1; y <= Grid.getHeight(); y++) {
            System.out.printf("[%2d]", y);
            for (int x = 1; x <= Grid.getWidth(); x++) {
                GridPoint gridPoint = new GridPoint(x, y);
                Shot theShot = theShooter.trackingGrid.wasShotAt(gridPoint);
                ShipModel theShip = theShooter.primaryGrid.shipPositions.get(gridPoint);

                if (theShot == null && theShip == null) { // Never shot at.
                    System.out.print("~"); // .
                    continue;
                }
                if (theShot != null && theShip == null) { // On the winner's tracking grid.
                    System.out.print("`"); // t
                    continue;
                }
                if (theShot == null && theShip != null) { // On the loser's primary grid.
                    System.out.print("_"); // p
                    continue;
                }

                HashSet<ShotResult> hitResults = new HashSet<>();
                ShotResult[] okShotResults = {ShotResult.Hit, ShotResult.Sunk};
                hitResults.addAll(Arrays.asList(okShotResults));

                // Here the theShot and theShip are not null.
                if (hitResults.contains(theShot.shotResult) && hitResults.contains(theShip.status)) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

}
