package conway.main;

import java.util.ArrayList;
import java.util.List;

public class BinaryTextWorldFactory {

    private List<String> rows;

    public BinaryTextWorldFactory() {
        rows = new ArrayList<String>();
    }

    public void addRow(String row) {
        rows.add(row);
    }

    public World build() {
        World world = new World(rows.size(), numberOfColumns());

        return world;
    }

    private int numberOfColumns() {
        if(rows.size()>0){
            return rows.get(0).length();
        }
        return 0;
    }
}
