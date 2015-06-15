package gamecontainer.strategy;

import gamecontainer.Coord;
import gamecontainer.Markers;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Василий on 14.06.2015.
 */
public class angleSquareStrategy extends StrategyPlayer {
    public angleSquareStrategy(Markers[][] board, Markers selfMarker) {
        super(board, selfMarker);
    }

    @Override
    public Coord getPosition() {
        ArrayList<Coord> list = new ArrayList<Coord>();
        if(isEmpty(0, 0)) list.add(new Coord(0, 0));
        if(isEmpty(getSizeBoard() - 1, 0)) list.add(new Coord(getSizeBoard() - 1, 0));
        if(isEmpty(0, getSizeBoard() - 1)) list.add(new Coord(0, getSizeBoard() - 1));
        if(isEmpty(getSizeBoard() - 1, getSizeBoard() - 1)) list.add(new Coord(getSizeBoard() - 1, getSizeBoard() - 1));

        if(list.size() > 0) {
            return  list.get((new Random()).nextInt(list.size()));
        }

        return null;
    }
}
