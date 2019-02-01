package conway.main;

import java.util.List;

public class WorldPrinter {

    private BinaryTextWorldFactory binaryTextWorldFactory = new BinaryTextWorldFactory();
    public void print(World w){
        List<String> strings = binaryTextWorldFactory.toRows(w);
        for(String s : strings){
            String p = "";
            for (int i = 0; i < s.length(); i++) {
                p += s.charAt(i) == '1' ? "[|||]":
                                          "     ";
            }
            System.out.println(p);
        }
    }
}
