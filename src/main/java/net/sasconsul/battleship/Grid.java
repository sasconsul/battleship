package net.sasconsul.battleship;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
public class Grid {
    @Getter private static int height = 10; // dimensions of the playing field
    @Getter private static int width = 10; // dimensions of the playing field

    static void setGridSize(int h, int w) {
        Grid.height = h;
        Grid.width = w;
    }

    public void printGridHeader() {
        //Header
        System.out.printf("    ");
        for (int x = 0; x < Grid.getWidth(); x++) {
//            System.out.printf("%c ", (char) ('a' + x));
            System.out.printf("%d ", (x+1)%10);
        }
        System.out.println();
    }

}
