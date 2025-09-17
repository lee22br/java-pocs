package J513;

import java.awt.*;

public class Square extends Rectangle {
    public Square(Color color, int area) {
        if (area < 0) throw new IllegalArgumentException();
        double sideLength = Math.sqrt(area);
        //super isn´t be in the first place anymore
        super(color, sideLength, sideLength);
    }
}

