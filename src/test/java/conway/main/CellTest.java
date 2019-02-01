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
    public void itGetsTheRightNeighbors(){
        List<Cell> neighbors = world.getCell(4, 4).getNeighbors(world);
        assertEquals(8, neighbors.size());
        assertEquals(3, neighbors.get(0).row);
        assertEquals(3, neighbors.get(0).column);

        assertEquals(3, neighbors.get(1).row);
        assertEquals(4, neighbors.get(1).column);

        assertEquals(3, neighbors.get(2).row);
        assertEquals(5, neighbors.get(2).column);

        assertEquals(4, neighbors.get(3).row);
        assertEquals(3, neighbors.get(3).column);

        assertEquals(4, neighbors.get(4).row);
        assertEquals(5, neighbors.get(4).column);

        assertEquals(5, neighbors.get(5).row);
        assertEquals(3, neighbors.get(5).column);

        assertEquals(5, neighbors.get(6).row);
        assertEquals(4, neighbors.get(6).column);

        assertEquals(5, neighbors.get(7).row);
        assertEquals(5, neighbors.get(7).column);
    }

    @Test
    public void itKnowsHowManyNeighborsAreAlive() {
        Cell cell = world.getCell(4, 4);
        assertEquals(0, cell.numberOfLivingNeighbors(world));
        cell.setAlive(true);
        world.getCell(3, 3).setAlive(true);
        world.getCell(3, 4).setAlive(true);
        world.getCell(3, 5).setAlive(true);
        world.getCell(4, 3).setAlive(true);
        world.getCell(4, 5).setAlive(true);
        world.getCell(5, 5).setAlive(true);
        assertEquals(6, cell.numberOfLivingNeighbors(world));
    }

    @Test
    public void itKnowsHowManyNeighborsAreAliveWhenOnEdge() {
        Cell cell = world.getCell(0, 0);
        assertEquals(0, cell.numberOfLivingNeighbors(world));
        world.getCell(0, 0).setAlive(true);
        world.getCell(0, 1).setAlive(true);
        assertEquals(1, cell.numberOfLivingNeighbors(world));
        world.getCell(1, 0).setAlive(true);
        world.getCell(1, 3).setAlive(true);
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
        Cell cell = world.getCell(4, 4);
        world.getCell(5, 3).setAlive(true);
        world.getCell(5, 5).setAlive(true);
        assertFalse(cell.isUnderpopulated(world));
        assertFalse(cell.isOverpopulated(world));
        world.getCell(3, 5).setAlive(true);
        assertFalse(cell.isUnderpopulated(world));
        assertFalse(cell.isOverpopulated(world));
    }

    @Test
    public void itKnowsWhenWellPopulated3() {
        Cell cell = world.getCell(4, 4);
        world.getCell(5, 3).setAlive(true);
        world.getCell(5, 5).setAlive(true);
        assertFalse(cell.isUnderpopulated(world));
        assertFalse(cell.isOverpopulated(world));
        world.getCell(3, 5).setAlive(true);
        assertFalse(cell.isUnderpopulated(world));
        assertFalse(cell.isOverpopulated(world));
    }

    @Test
    public void itKnowsWhenOverPopulated() {
        Cell cell = world.getCell(1, 1);
        assertFalse(cell.isOverpopulated(world));
        world.getCell(0, 0).setAlive(true);
        world.getCell(0, 1).setAlive(true);
        world.getCell(2, 2).setAlive(true);
        world.getCell(2, 1).setAlive(true);
        assertTrue(cell.isOverpopulated(world));
    }




    @Test
    public void itDiesIfUnderPopulated() {

        Cell cell = world.getCell(1, 1);
        cell.setAlive(true);
        cell.cycle(world);
        world.commit();

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
        assertFalse(cell.willBeAlive());
        assertTrue(cell.isAlive());
    }


    @Test
    public void itKnowsIfInFertileTerritory() {
        Cell cell = world.getCell(1, 1);
        cell.setAlive(false);
        assertFalse(cell.isInFertileTerritory(world));
        world.getCell(0, 0).setAlive(true);
        assertFalse(cell.isInFertileTerritory(world));
        world.getCell(0, 1).setAlive(true);
        assertFalse(cell.isInFertileTerritory(world));
        world.getCell(0, 2).setAlive(true);
        assertTrue(cell.isInFertileTerritory(world));
    }

    @Test
    public void itIsBornIfInFertileTerritory() {
        Cell cell = world.getCell(1, 1);
        cell.setAlive(false);
        world.getCell(0, 0).setAlive(true);
        world.getCell(0, 1).setAlive(true);
        world.getCell(0, 2).setAlive(true);
        cell.cycle(world);
        assertTrue(cell.willBeAlive());
        assertFalse(cell.isAlive());
    }

    @Test
    public void itStaysDeadOutsideFertileTerritory() {
        Cell cell = world.getCell(1, 1);
        cell.setAlive(false);
        world.getCell(0, 1).setAlive(true);
        world.getCell(0, 2).setAlive(true);
        cell.cycle(world);
        assertFalse(cell.willBeAlive());
        assertFalse(cell.isAlive());
    }

}