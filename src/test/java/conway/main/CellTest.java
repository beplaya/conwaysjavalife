package conway.main;

import conway.main.Cell;
import conway.main.World;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CellTest {

    private World world;

    @Before
    public void setup(){
        world = new World(10, 10);
    }

    @Test
    public void itDiesIfUnderPopulated(){

        Cell cell = world.getCell(1,1);
        cell.setAlive(true);


        cell.cycle(world);

        Assert.assertFalse(cell.isAlive());
    }
}