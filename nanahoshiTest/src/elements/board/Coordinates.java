package elements.board;

import java.util.ArrayList;
import java.util.Iterator;

public class Coordinates {

    public int x;
    public int y;

    private static String[] xStrings = {"A", "B", "C", "D"};
    private static String[] yStrings = {"1", "2", "3", "4"};
    private static ArrayList<Coordinates> legalCoordinates;

    static{
        legalCoordinates = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if( !(x % 3 == 0 && y % 3 == 0) )
                    legalCoordinates.add(new Coordinates(x,y));
            }
        }
    }

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Iterator<Coordinates> getLegal(){
        return legalCoordinates.iterator();
    }

    public boolean isLegal(){
        return x >= 0 && x < 4 && y >= 0 && y < 4 && (x % 3 + y % 3 != 0);
    }

    public String toString() {
        return xStrings[x] + yStrings[y];
    }

}
