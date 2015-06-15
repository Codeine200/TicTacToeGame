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
        return (isEmpty(1, 1)) ? new Coord(1, 1) : null;
    }
}
