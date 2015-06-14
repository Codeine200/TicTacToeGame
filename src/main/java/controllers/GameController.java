package controllers;

import gamecontainer.*;
import views.BoardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Василий on 13.06.2015.
 */
public class GameController {
    GameModel model;
    BoardView boardView;

    Enemy comp;
    Me me;

    public GameController(GameModel model, BoardView boardView) {
        this.model = model;
        this.boardView = boardView;

        comp = new Enemy(Markers.TOE);
        me = new Me(Markers.CROSS);

        this.boardView.addSquareActionListener(new SquareListener());

    }

    private class SquareListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String id = ((JButton)e.getSource()).getName();
            int row = Integer.parseInt(id.substring(0, id.indexOf(":")));
            int column = Integer.parseInt(id.substring(id.indexOf(":")+1, id.length()));

            System.out.println("You clicked the button " + row + ":" + column);

            me.setCoord(row, column);
            GameMessage message = model.move(me, me.getCoord().getRow(), me.getCoord().getColumn());
            if(message == GameMessage.ERROR_NOT_EMPTY_FIELD || message == GameMessage.ERROR_ALREADY_MOVE) {
                System.out.println(message);
                return;
            }
            System.out.println(message);
            boardView.setIcon("resource/" + me.getMarker() + ".png", row, column);

            if(message == GameMessage.INFO_WINNER) {
                System.out.println("You won");
                return;
            }

            comp.setArea(model.getArea());
            comp.move();
            message = model.move(comp, comp.getCoord().getRow(), comp.getCoord().getColumn());
            boardView.setIcon("resource/" + comp.getMarker() + ".png", comp.getCoord().getRow(), comp.getCoord().getColumn());
            System.out.println(message + " " + comp.getCoord().getRow() + ":" + comp.getCoord().getColumn());
            if(message == GameMessage.INFO_WINNER) {
                System.out.println("You lost");
                return;
            }
        }
    }
}
