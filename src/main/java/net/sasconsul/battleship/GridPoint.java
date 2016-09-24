package net.sasconsul.battleship;


import lombok.Data;

@Data
public class GridPoint {
    private final int x;
    private final int y;

    public GridPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)",x,y);
    }

    // Need to impl equal and hashcode for the HashMaps to work properly.

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  GridPoint)) return false;

        GridPoint gridPoint = (GridPoint) o;

        return x == gridPoint.getX() && y == gridPoint.getY();
    }

    @Override
    public final int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}