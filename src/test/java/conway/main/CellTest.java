package conway.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        world.getCell(0, 0).setAlive(true);
        world.getCell(0, 1).setAlive(true);
        world.getCell(1, 0).setAlive(true);
        world.getCell(1, 3).setAlive(true);
        assertEquals(3, cell.numberOfLivingNeighbors(world));
    }

    @Test
    public void itKnowsHowManyNeighborsAreAliveWhenOnEdge() {
        Cell cell = world.getCell(0, 0);
        assertEquals(0, cell.numberOfLivingNeighbors(world));
        world.getCell(0, 0).setAlive(true);
        world.getCell(0, 1).setAlive(true);
        world.getCell(1, 0).setAlive(true);
        world.getCell(1, 3).setAlive(true);
        assertEquals(2, cell.numberOfLivingNeighbors(world));
    }

    @Test
    public void itDiesIfUnderPopulated() {

        Cell cell = world.getCell(1, 1);
        cell.setAlive(true);
        cell.cycle(world);

        assertFalse(cell.isAlive());
    }
}