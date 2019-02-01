package conway.main;

import java.util.ArrayList;
import java.util.List;

public class BinaryTextWorldFactory {

    private static final int MIN_COLUMN_COUNT = 3;
    private List<String> rows;

    public BinaryTextWorldFactory() {
        rows = new ArrayList<String>();
    }

    public void addRow(String row) {
        if(row.length() < MIN_COLUMN_COUNT){
            throw new IllegalArgumentException(String.format("Cannot add row with column count under %d", MIN_COLUMN_COUNT));
        }
        if (rows.size() > 0) {
            if (rows.get(0).length() != row.length()){
                throw new IllegalArgumentException(String.format("Cannot change column count from %d to %d", rows.get(0).length(), row.length()));
            }
        }
        rows.add(row);
    }

    public World build() {
        World world = new World(rows.size(), numberOfColumns());
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            String rowStr = rows.get(rowIndex);
            for (int colIndex = 0; colIndex < rowStr.length(); colIndex++) {
                world.getCell(rowIndex, colIndex).setAlive(rowStr.charAt(colIndex) == '1');
            }
        }
        return world;
    }

    private int numberOfColumns() {
        if (rows.size() > 0) {
            return rows.get(0).length();
        }
        return 0;
    }
}
