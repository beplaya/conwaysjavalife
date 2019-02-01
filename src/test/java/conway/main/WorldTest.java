package conway.main;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WorldTest {

    private World world;

    @Before
    public void setup() {
        world = new World(10, 10);
    }

    @Test
    public void itGetsACell() {
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
        world.commit();
        List<String> strings = binaryTextWorldFactory.toRows(world);
        for(String r : strings){
            System.out.println(r);
        }
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


    @Test
    public void itKills() {

        BinaryTextWorldFactory binaryTextWorldFactory = new BinaryTextWorldFactory();
        binaryTextWorldFactory.addRow("0000000");//0000000
        binaryTextWorldFactory.addRow("0000000");//0000000
        binaryTextWorldFactory.addRow("0000100");//0000100
        binaryTextWorldFactory.addRow("0011100");//0010010
        binaryTextWorldFactory.addRow("0011100");//0010100
        binaryTextWorldFactory.addRow("0000000");//0001000
        World world = binaryTextWorldFactory.build();
        world.cycle();
        world.commit();
        List<String> actualRows = binaryTextWorldFactory.toRows(world);
        List<String> expectedRows = new ArrayList<String>() {{
            add("0000000");
            add("0000000");
            add("0000100");
            add("0010010");
            add("0010100");
            add("0001000");
        }};
        String actual = "";
        String expected = "";
        for (int i = 0; i < actualRows.size(); i++) {
            actual += actualRows.get(i) + "\n";
            expected += expectedRows.get(i) + "\n";
        }
        assertEquals(expected, actual);

    }
}