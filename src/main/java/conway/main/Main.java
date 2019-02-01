package conway.main;

import java.io.IOException;
import java.util.Random;

public class Main {


    //    Any live cell with fewer than two live neighbors dies, as if by underpopulation.
//    Any live cell with two or three live neighbors lives on to the next generation.
//    Any live cell with more than three live neighbors dies, as if by overpopulation.
//    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    public static void main(String[] args) throws InterruptedException, IOException {
        BinaryTextWorldFactory binaryTextWorldFactory = new BinaryTextWorldFactory();
        int size = 50;
        for (int i = 0; i < size; i++) {
            binaryTextWorldFactory.addRow(randomBinaryString(size));
        }
        World w = binaryTextWorldFactory.build();

        WorldPrinter worldPrinter = new WorldPrinter();
        for (int i = 0; i < 500; i++) {
            Thread.sleep(20);
            worldPrinter.print(w);
            w.cycle();
            System.out.println("====================================");
        }
    }

    private static String randomBinaryString(int length) {
        Random random = new Random();
        String rbs = "";
        for (int i = 0; i < length; i++) {
            rbs += random.nextBoolean() ? "1" : "0";
        }
        return rbs;
    }
}
