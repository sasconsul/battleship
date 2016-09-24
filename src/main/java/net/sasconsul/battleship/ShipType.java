package net.sasconsul.battleship;

import lombok.Data;

/**
 * Created by sasconsul on 8/27/16.
 */


// From https://en.wikipedia.org/wiki/Battleship_(game)
// one version of the complements of shipPositions
public enum ShipType {
    AirCarrier(1, 5),
    BattleShip(1, 4),
    Submarine(1, 3),
    Cruiser(1, 3),
    Patrol(1, 2);

    private int count;

    private final int size;

    ShipType(final int num, final int size) {
        this.count = num;
        this.size = size;
    }

    public int size() {
        return size;
    }

    public int num() {
        return count;
    }
}