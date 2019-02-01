package conway.main;

import sun.security.krb5.internal.PAData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PainterGUI extends JFrame {

    private final Painting painting;

    public PainterGUI() {
        setSize(1000, 1000);
        setVisible(true);

        painting = new Painting();
        setContentPane(painting);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        painting.setLayout(flowLayout);
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
            g.clearRect(0, 0, getWidth(), getHeight());

            if (world != null) {

                int cellWidth = Math.round ((float) getWidth() / (float) world.width());
                int cellHeight = Math.round  ((float) getHeight() / (float) world.height());


                for (int r = 0; r < world.height(); r++) {
                    for (int c = 0; c < world.width(); c++) {
                        Cell cell = world.getCell(r, c);
                        paintCell(cell, g, cellWidth, cellHeight);
                    }
                }

            }
        }


        private void paintCell(Cell cell, Graphics g, int cellWidth, int cellHeight) {
            g.setColor(cell.isAlive() ? getRandomColor() : Color.BLACK);
            int startX = (int) (cell.column * (float)cellWidth);
            int startY = (int) (cell.row * (float)cellHeight);
            g.fillRect(startX, startY, startX + cellWidth, startY + cellHeight);
            g.setColor(Color.BLACK);
            g.drawRect(startX, startY, startX + cellWidth, startY + cellHeight);
        }

        private Color getRandomColor() {
            return new Color(255, randomColorComponent(), randomColorComponent());
        }

        private int randomColorComponent() {
            return random.nextInt(155);
        }


        public void setWorld(World world) {
            this.world = world;
        }
    }
}