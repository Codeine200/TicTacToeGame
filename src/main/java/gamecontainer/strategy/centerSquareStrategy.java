package gamecontainer.strategy;

import gamecontainer.Coord;
import gamecontainer.Markers;

/**
 * Created by Василий on 15.06.2015.
 */
public class centerSquareStrategy extends StrategyPlayer {


    public centerSquareStrategy(Markers[][] board, Markers selfMarker) {
        super(board, selfMarker);
    }
    @Override
    public Coord getPosition() {
        return (isEmpty((int)(board.length/2), (int)(board.length/2))) ? new Coord((int)(board.length/2), (int)(board.length/2)) : null;
    }
}
