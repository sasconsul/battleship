package net.sasconsul.battleship.test;

import net.sasconsul.battleship.GridPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by sasconsul on 9/5/16.
 * Copyright Stuart Schmukler 2016
 */

@RunWith(Parameterized.class)
public class GridPoint2Test {
    private GridPoint p1;
    private GridPoint p2;
    private boolean equal;

    public GridPoint2Test(int x1, int y1, int x2, int y2, boolean equal) {
        this.p1 = new GridPoint(x1, y1);
        this.p2 = new GridPoint(x2, y2);
        this.equal = equal;
    }

    @Parameters(name = "{index}: p1({0},{1}) p2({2},{3}) = {4}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 2, 1, 2, true},
                {1, 2, 1, 3, false},
                {2, 2, 3, 2, false}
// etc.
        });
    }

    // Test methods
    @Test
    public void testEquals() {
        Assert.assertEquals(equal, p1.equals(p2));
    }

    @Test
    public void neverEqualsNull() {
        Assert.assertNotEquals(p1, null);
    }

    @Test
// Can be violated by accident.
    public void hashCodesNotEqual() {
        Assert.assertEquals(equal, p1.hashCode() == p2.hashCode());
    }
}