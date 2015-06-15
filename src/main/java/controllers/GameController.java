package controllers;

import gamecontainer.*;
import gamecontainer.strategy.*;
import views.BoardView;
import views.PanelToolsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Василий on 13.06.2015.
 */
public class GameController {
    GameModel model;
    BoardView boardView;
    PanelToolsView toolsView;

    Enemy comp;
    Me me;

    public GameController(GameModel model, BoardView boardView, PanelToolsView toolsView) {
        this.model = model;
        this.boardView = boardView;
        this.toolsView = toolsView;

        comp = new Enemy(Markers.ZERO);
        voidFillStrategy();

        me = new Me(Markers.CROSS);

        this.boardView.addSquareActionListener(new SquareListener());
        this.toolsView.addButtonListener(new StartGameListener());

    }

    private class StartGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boardView.clear();
            model.clear();
            toolsView.setMessage("");
            int n = toolsView.getN();
            n = (n == 0 || n > 10) ? 3 : n;
            toolsView.setN(n);
            model.setSize(n);
            boardView.createBoard(n);
            comp.removeAllStrategy();
            voidFillStrategy();
        }
    }

    private class SquareListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String id = ((JButton)e.getSource()).getName();
            int row = Integer.parseInt(id.substring(0, id.indexOf(":")));
            int column = Integer.parseInt(id.substring(id.indexOf(":") + 1, id.length()));

            me.setCoord(row, column);
            GameMessage message = model.move(me, me.getCoord().getRow(), me.getCoord().getColumn());
            System.out.println(message);
            switch(message) {
                case ERROR_NOT_EMPTY_FIELD:
                case ERROR_ALREADY_MOVE:
                case INFO_END_GAME:
                    return;

                case INFO_WINNER:
                    boardView.setText(me.getMarker().toString(), row, column);
                    toolsView.setMessage("You won. Start game");
                    return;

                case ERROR_FILL_ALL_FIELD:
                    toolsView.setMessage("Dead heat. Start game");
                    return;

                default:
                    boardView.setText(me.getMarker().toString(), row, column);
            }

            comp.setArea(model.getArea());
            comp.move();
            if(comp.getCoord() == null) {
                toolsView.setMessage("Dead heat. Start game");
                return;
            }

            message = model.move(comp, comp.getCoord().getRow(), comp.getCoord().getColumn());
            boardView.setText(comp.getMarker().toString(), comp.getCoord().getRow(), comp.getCoord().getColumn());
            System.out.println(message);
            if(message == GameMessage.INFO_WINNER) {
                toolsView.setMessage("You lost. Start game");
                return;
            }
        }
    }

    private void voidFillStrategy() {
        comp.addStrategy(new checkWinPathStrategy(model.getArea(), comp.getMarker()));
        comp.addStrategy(new checkProtectStrategy(model.getArea(), comp.getMarker()));
        comp.addStrategy(new centerSquareStrategy(model.getArea(), comp.getMarker()));
        comp.addStrategy(new angleSquareStrategy(model.getArea(), comp.getMarker()));
        comp.addStrategy(new emptySquareStrategy(model.getArea(), comp.getMarker()));

    }
}
