package net.sasconsul.battleship;


import java.util.Random;

public enum Orientation {
    V, H; // V=vertical, H=horizontal

    private static Random rand = new Random();

    public boolean isHorizonal() {
        return this == Orientation.H;
    }

    static public Orientation randOrientation() {
        return rand.nextBoolean() ? Orientation.V : Orientation.H;
    }
}