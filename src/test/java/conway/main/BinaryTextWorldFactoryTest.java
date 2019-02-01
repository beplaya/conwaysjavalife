package conway.main;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinaryTextWorldFactoryTest {

    private BinaryTextWorldFactory binaryTextWorldFactory;

    @Before
    public void setup() {
        binaryTextWorldFactory = new BinaryTextWorldFactory();
    }

    @Test
    public void itCreatesAWorldByRows() {
        binaryTextWorldFactory.addRow("000");
        binaryTextWorldFactory.addRow("000");
        binaryTextWorldFactory.addRow("000");
        World world = binaryTextWorldFactory.build();
        assertEquals(3, world.numberOfColumns());
        assertEquals(3, world.numberOfRows());
    }

    @Test(expected = IllegalArgumentException.class)
    public void itRejectsColumnCountChange() {
        binaryTextWorldFactory.addRow("000");
        binaryTextWorldFactory.addRow("0000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void itRejectsInssuficientColumnCount() {
        binaryTextWorldFactory.addRow("");
    }

    @Test
    public void itParsesTheWorld() {
        binaryTextWorldFactory.addRow("100");
        binaryTextWorldFactory.addRow("001");
        binaryTextWorldFactory.addRow("110");
        World world = binaryTextWorldFactory.build();
        assertEquals(true, world.getCell(0, 0).isAlive());
        assertEquals(false, world.getCell(0, 1).isAlive());
        assertEquals(false, world.getCell(0, 2).isAlive());
        assertEquals(false, world.getCell(1, 0).isAlive());
        assertEquals(false, world.getCell(1, 1).isAlive());
        assertEquals(true, world.getCell(1, 2).isAlive());
        assertEquals(true, world.getCell(2, 0).isAlive());
        assertEquals(true, world.getCell(2, 1).isAlive());
        assertEquals(false, world.getCell(2, 2).isAlive());
    }

    @Test
    public void itConvertsWorldToStrings() {
        World world = new World(3, 4);
        world.getCell(0, 1).setAlive(true);
        world.getCell(1, 3).setAlive(true);
        world.getCell(2, 2).setAlive(true);
        List<String> rows = binaryTextWorldFactory.toRows(world);
        assertEquals(world.numberOfRows(), rows.size());
        assertEquals("0100", rows.get(0));
        assertEquals("0001", rows.get(1));
        assertEquals("0010", rows.get(2));
    }

}