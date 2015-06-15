package gamecontainer;

import gamecontainer.strategy.StrategyPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Василий on 12.06.2015.
 */
public class Enemy implements Player {
    private Markers marker;
    private Markers[][] area;

    private ArrayList<StrategyPlayer> strategyList = new ArrayList<StrategyPlayer>();

    private Coord coord;

    public Enemy(Markers value) {
        this.marker = value;
    }

    public void setArea(Markers[][] area) {
        this.area = area;
    }

    @Override
    public Coord getCoord() {
        return  (coord == null) ? null : new Coord(coord.getRow(), coord.getColumn());
    }


    public void move() {
        coord = null;
        for(StrategyPlayer strategy : strategyList) {
            coord = strategy.getPosition();
            System.out.println("Comp chosen " + strategy);
            if(coord != null) return;;
        }
    }

    @Override
    public Markers getMarker() {
        return marker;
    }

    public void addStrategy(StrategyPlayer strategy) {
        strategyList.add(strategy);
    }
}
