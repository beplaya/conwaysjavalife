package conway.main;

public class Cell {
    private boolean alive;
    public final int row;
    public final int column;

    public Cell(int row, int column) {

        this.row = row;
        this.column = column;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void cycle(World world) {

    }
}
