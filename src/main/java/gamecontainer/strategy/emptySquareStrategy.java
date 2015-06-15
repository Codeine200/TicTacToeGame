package gamecontainer.strategy;

import gamecontainer.Coord;
import gamecontainer.Markers;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Василий on 14.06.2015.
 */
public class emptySquareStrategy extends StrategyPlayer {
    public emptySquareStrategy(Markers[][] board, Markers selfMarker) {
        super(board, selfMarker);
    }

    @Override
    public Coord getPosition() {
        ArrayList<Coord> list = new ArrayList<Coord>();
        for(int i=0; i<board.length; i++)
            for(int j=0; j<board.length; j++)
                if(isEmpty(i, j)) list.add(new Coord(i, j));

        if(list.size() > 0) {
            return  list.get((new Random()).nextInt(list.size()));
        }

        return null;
    }
}
