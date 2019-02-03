package conway.main;

import java.util.Random;

public class Main {


    public static final int MIN_PERIOD = 10;
    public static final int PERIOD_STEP = 100;
    public static final int MAX_PERIOD = 5210;
    public static final int WORLD_SIZE = 41;
    public static int PERIOD = 10;
    public static boolean run = false;
    private static World world;

    //    Any live cell with fewer than two live neighbors dies, as if by underpopulation.
//    Any live cell with two or three live neighbors lives on to the next generation.
//    Any live cell with more than three live neighbors dies, as if by overpopulation.
//    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    public static void main(String[] args) {
        final PainterGUI painterGUI = new PainterGUI();
        new ControlGUI();
        resetWorld();

        new Thread(new Runnable() {
            public void run() {
                painterGUI.update(world);
                while (true) {
                    try {
                        Thread.sleep(PERIOD);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (run) {
                        painterGUI.update(world);
                        world.cycle();
                        world.commit();
                    }
                }
            }
        }).start();
    }

    public static void resetWorld() {
        final BinaryTextWorldFactory binaryTextWorldFactory = new BinaryTextWorldFactory();
        int size = WORLD_SIZE;
        for (int i = 0; i < size; i++) {
            binaryTextWorldFactory.addRow(randomBinaryString(size));
        }
        world = binaryTextWorldFactory.build();
    }

    private static String randomBinaryString(int length) {
        Random random = new Random();
        String rbs = "";
        for (int i = 0; i < length; i++) {
            rbs += random.nextBoolean() ? "1" : "0";
        }
        return rbs;
    }

    public static void increaseSpeed() {
        if (PERIOD > MIN_PERIOD ) {
            PERIOD -= PERIOD_STEP;
        }
    }
    public static void decreaseSpeed() {
        if (PERIOD < MAX_PERIOD) {
            PERIOD += PERIOD_STEP;
        }
    }
}
