import controllers.GameController;
import gamecontainer.*;
import views.BoardView;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Василий on 12.06.2015.
 */
public class Main {


    public static void main(String[] args) {
        System.out.println("Start Game");

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("TicTacToe Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameModel model = new GameModel(3);
        BoardView boardView = new BoardView(3);
        new GameController(model, boardView);

        frame.getContentPane().add(boardView);
        frame.setSize(new Dimension(300, 300));

        frame.setVisible(true);
    }

}
