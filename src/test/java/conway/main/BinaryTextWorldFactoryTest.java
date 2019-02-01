package conway.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTextWorldFactoryTest {

    private BinaryTextWorldFactory binaryTextWorldFactory;

    @Before
    public void setup(){
        binaryTextWorldFactory = new BinaryTextWorldFactory();
    }

    @Test
    public void itCreatesAWorldByRows(){
        binaryTextWorldFactory.addRow("000");
        binaryTextWorldFactory.addRow("000");
        binaryTextWorldFactory.addRow("000");
        World world = binaryTextWorldFactory.build();
        assertEquals(3, world.width());
        assertEquals(3, world.height());

    }
}