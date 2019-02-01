package conway.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {

    private World world;

    @Before
    public void setup(){
        world = new World(10, 10);
    }

    @Test
    public void itGetsACell(){
        Cell cell = world.getCell(5, 7);
        assertEquals(5, cell.row);
        assertEquals(7, cell.column);
    }

    @Test
    public void itCyclesTheWorld() {
        BinaryTextWorldFactory binaryTextWorldFactory = new BinaryTextWorldFactory();
        binaryTextWorldFactory.addRow("100");
        binaryTextWorldFactory.addRow("011");
        binaryTextWorldFactory.addRow("111");
        World world = binaryTextWorldFactory.build();
        assertEquals(true, world.getCell(0, 0).isAlive());
        assertEquals(false, world.getCell(0, 1).isAlive());
        assertEquals(false, world.getCell(0, 2).isAlive());
        assertEquals(false, world.getCell(1, 0).isAlive());
        assertEquals(true, world.getCell(1, 1).isAlive());
        assertEquals(true, world.getCell(1, 2).isAlive());
        assertEquals(true, world.getCell(2, 0).isAlive());
        assertEquals(true, world.getCell(2, 1).isAlive());
        assertEquals(true, world.getCell(2, 2).isAlive());
        world.cycle();
        assertEquals(false, world.getCell(0, 0).isAlive());
        assertEquals(true, world.getCell(0, 1).isAlive());
        assertEquals(false, world.getCell(0, 2).isAlive());
        //
        assertEquals(false, world.getCell(1, 0).isAlive());
        assertEquals(false, world.getCell(1, 1).isAlive());
        assertEquals(true, world.getCell(1, 2).isAlive());
        //
        assertEquals(true, world.getCell(2, 0).isAlive());
        assertEquals(false, world.getCell(2, 1).isAlive());
        assertEquals(true, world.getCell(2, 2).isAlive());
    }
}