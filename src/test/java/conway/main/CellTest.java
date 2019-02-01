package conway.main;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CellTest {

    private World world;

    @Before
    public void setup() {
        world = new World(10, 10);
    }

    @Test
    public void itKnowsHowManyNeighborsAreAlive() {
        Cell cell = world.getCell(1, 1);
        assertEquals(0, cell.numberOfLivingNeighbors(world));
        world.getCell(0, 0).setWasAlive(true);
        world.getCell(0, 1).setWasAlive(true);
        world.getCell(1, 0).setWasAlive(true);
        world.getCell(1, 3).setWasAlive(true);
        assertEquals(3, cell.numberOfLivingNeighbors(world));
    }

    @Test
    public void itKnowsHowManyNeighborsAreAliveWhenOnEdge() {
        Cell cell = world.getCell(0, 0);
        assertEquals(0, cell.numberOfLivingNeighbors(world));
        world.getCell(0, 0).setWasAlive(true);
        world.getCell(0, 1).setWasAlive(true);
        world.getCell(1, 0).setWasAlive(true);
        world.getCell(1, 3).setWasAlive(true);
        assertEquals(2, cell.numberOfLivingNeighbors(world));
    }

    @Test
    public void itKnowsWhenUnderPopulated() {
        Cell cell = world.getCell(1, 1);
        assertTrue(cell.isUnderpopulated(world));
        world.getCell(0, 1).setAlive(true);
        assertTrue(cell.isUnderpopulated(world));
    }

    @Test
    public void itKnowsWhenWellPopulated() {
        Cell cell = world.getCell(1, 1);
        world.getCell(0, 0).setWasAlive(true);
        world.getCell(0, 1).setWasAlive(true);
        assertFalse(cell.isUnderpopulated(world));
        assertFalse(cell.isOverpopulated(world));
        world.getCell(2, 2).setWasAlive(true);
        assertFalse(cell.isUnderpopulated(world));
        assertFalse(cell.isOverpopulated(world));
    }

    @Test
    public void itKnowsWhenOverPopulated() {
        Cell cell = world.getCell(1, 1);
        assertFalse(cell.isOverpopulated(world));
        world.getCell(0, 0).setWasAlive(true);
        world.getCell(0, 1).setWasAlive(true);
        world.getCell(2, 2).setWasAlive(true);
        world.getCell(2, 1).setWasAlive(true);
        assertTrue(cell.isOverpopulated(world));
    }



    @Test
    public void itKnowsWhenOverPopulated() {

        BinaryTextWorldFactory binaryTextWorldFactory = new BinaryTextWorldFactory();
        binaryTextWorldFactory.addRow("0000000");
        binaryTextWorldFactory.addRow("0000000");
        binaryTextWorldFactory.addRow("0000100");
        binaryTextWorldFactory.addRow("0011100");
        binaryTextWorldFactory.addRow("0011100");
        binaryTextWorldFactory.addRow("0000000");
        World world = binaryTextWorldFactory.build();
        world.cycle();
        List<String> strings = binaryTextWorldFactory.toRows(world);
        List<String> expectedRows = new ArrayList<String>(){{
            add("")
        }}


    }


    @Test
    public void itDiesIfUnderPopulated() {

        Cell cell = world.getCell(1, 1);
        cell.setAlive(true);
        cell.cycle(world);

        assertFalse(cell.isAlive());
    }


    @Test
    public void itDiesIfOverPopulated() {

        Cell cell = world.getCell(1, 1);
        cell.setAlive(true);
        world.getCell(0, 0).setAlive(true);
        world.getCell(0, 1).setAlive(true);
        world.getCell(0, 2).setAlive(true);
        world.getCell(1, 0).setAlive(true);
        cell.cycle(world);
        assertFalse(cell.isAlive());
        assertTrue(cell.wasAlive());
    }


    @Test
    public void itKnowsIfInFertileTerritory() {
        Cell cell = world.getCell(1, 1);
        cell.setAlive(false);
        assertFalse(cell.isInFertileTerritory(world));
        world.getCell(0, 0).setWasAlive(true);
        assertFalse(cell.isInFertileTerritory(world));
        world.getCell(0, 1).setWasAlive(true);
        assertFalse(cell.isInFertileTerritory(world));
        world.getCell(0, 2).setWasAlive(true);
        assertTrue(cell.isInFertileTerritory(world));
    }

    @Test
    public void itIsBornIfInFertileTerritory() {
        Cell cell = world.getCell(1, 1);
        cell.setAlive(false);
        world.getCell(0, 0).setWasAlive(true);
        world.getCell(0, 1).setWasAlive(true);
        world.getCell(0, 2).setWasAlive(true);
        cell.cycle(world);
        assertTrue(cell.isAlive());
        assertFalse(cell.wasAlive());
    }

    @Test
    public void itStaysDeadOutsideFertileTerritory() {
        Cell cell = world.getCell(1, 1);
        cell.setAlive(false);
        world.getCell(0, 1).setAlive(true);
        world.getCell(0, 2).setAlive(true);
        cell.cycle(world);
        assertFalse(cell.isAlive());
        assertFalse(cell.wasAlive());
    }

}