package net.sasconsul.battleship.test;

import net.sasconsul.battleship.GridPoint;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sasconsul on 9/5/16.
 * Copyright Stuart Schmukler 2016
 *
 */
public class GridPointTest {
    @Test
    public void testEquals_Symmetric() {

        GridPoint a = new GridPoint(1,2);  // equals and hashCode check name field value
        GridPoint b = new GridPoint(1,2);
        Assert.assertTrue(a.equals(b) && b.equals(a));
        Assert.assertTrue(a.hashCode() == b.hashCode());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(GridPoint.class).verify();
    }
}