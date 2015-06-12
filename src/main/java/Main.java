import gamecontainer.*;

import java.util.Arrays;

/**
 * Created by Василий on 12.06.2015.
 */
public class Main {


    public static void main(String[] args) {
        System.out.println("Start Game");
        Area area = new Area(3);
        Enemy comp = new Enemy(Markers.TOE);
        Me me = new Me(Markers.CROSS);

        System.out.println("Step 1");
        me.setRow(0);
        me.setColumn(0);
        System.out.println(area.move(me));
        area.show();

        System.out.println("\nStep 2");
        comp.setArea(area.getArea());
        System.out.println(area.move(comp));
        area.show();


/*
        System.out.println("\nStep 4");
        setValue(Markers.TOE, 1, 0);
        show();
        System.out.println(Markers.TOE + " : " + isWinner(Markers.TOE));

        System.out.println("\nStep 5");
        setValue(Markers.CROSS, 0, 0);
        show();
        System.out.println(Markers.CROSS + " : " + isWinner(Markers.CROSS));
*/
    }


}
