package conway.main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PainterGUI extends JFrame {

    private final Painting painting;
    private static ColorMode colorMode = ColorMode.RAINBOW;

    public static void cycleColorMode() {
        int nextOrdinal = colorMode.ordinal() + 1;
        if (nextOrdinal >= ColorMode.values().length) {
            nextOrdinal = 0;
        }
        colorMode = ColorMode.values()[nextOrdinal];
    }

    public enum ColorMode {
        RED, GREEN, BLUE, RAINBOW
    }

    public PainterGUI() {
        int width = 1000;
        setSize(width, width);
        setVisible(true);

        painting = new Painting();
        setContentPane(painting);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        painting.setLayout(flowLayout);
        setLocation(300, 30);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void update(World world) {
        painting.setWorld(world);
        painting.revalidate();
        painting.repaint();
    }

    private class Painting extends JPanel {

        private World world;
        private Random random = new Random();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
//
//            g.clearRect(0, 0, getWidth(), getHeight());

            if (world != null) {

                int cellWidth = getCellWidth();
                int cellHeight = getCellHeight();


                for (int r = 0; r < world.numberOfRows(); r++) {
                    for (int c = 0; c < world.numberOfColumns(); c++) {
                        Cell cell = world.getCell(r, c);
                        paintCell(cell, g, cellWidth, cellHeight);
                    }
                }

            }
        }

        private int getCellHeight() {
            return Math.round((float) getHeight() / (float) world.numberOfRows());
        }

        private int getCellWidth() {
            return Math.round((float) getWidth() / (float) world.numberOfColumns());
        }


        private void paintCell(Cell cell, Graphics g, int cellWidth, int cellHeight) {
            g.setColor(Color.BLACK);
            int startX = getCellLeft(cell, cellWidth);
            int startY = getCellTop(cell, cellHeight);
            g.fillRect(startX, startY, cellWidth, cellHeight);
//            g.setColor(Color.BLACK);
//            g.drawRect(startX, startY, cellWidth, cellHeight);
            if (cell.isAlive()) {
                g.setColor(cell.isAlive() ? getRandomColor(colorMode) : Color.BLACK);
                float circleScale = .8f;
                g.fillOval(startX, startY, (int) (cellWidth * circleScale), (int) (cellHeight * circleScale));
            }
//            g.setColor(Color.WHITE);
//            g.drawString("(" + cell.row + "," + cell.column + ") ~ " + cell.numberOfLivingNeighbors(world), startX + 5, startY + 20);
        }

        private int getCellTop(Cell cell, float cellHeight) {
            return (int) (cell.row * cellHeight);
        }

        private int getCellLeft(Cell cell, float cellWidth) {
            return (int) (cell.column * cellWidth);
        }

        private Color getRandomColor(ColorMode colorMode) {
            switch (colorMode) {
                case RED:
                    return getRandomRedColor();
                case GREEN:
                    return getRandomGreenColor();
                case BLUE:
                    return getRandomBlueColor();
                default:
                case RAINBOW:
                    return getRandomRainbowColor();
            }
        }

        private Color getRandomRainbowColor() {
            int i = random.nextInt(3);
            if (i == 0) {
                return getRandomRedColor();
            } else if (i == 1) {
                return getRandomGreenColor();
            } else {
                return getRandomBlueColor();
            }
        }

        private Color getRandomRedColor() {
            return new Color(255, randomColorComponent(0, 155), randomColorComponent(0, 155));
        }


        private Color getRandomGreenColor() {
            return new Color(randomColorComponent(0, 155), 255, randomColorComponent(0, 155));
        }

        private Color getRandomBlueColor() {
            return new Color(randomColorComponent(0, 155), randomColorComponent(0, 155), 255);
        }

        private int randomColorComponent(int min, int max) {
            return min + random.nextInt(max - min);
        }


        public void setWorld(World world) {
            this.world = world;
        }
    }
}
