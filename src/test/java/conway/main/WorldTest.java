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
}