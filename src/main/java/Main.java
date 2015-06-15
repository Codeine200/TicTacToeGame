import controllers.GameController;
import gamecontainer.*;
import javafx.scene.layout.Border;
import views.BoardView;
import views.PanelToolsView;

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
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        GameModel model = new GameModel(3);
        BoardView boardView = new BoardView(3);
        PanelToolsView tools = new PanelToolsView();
        new GameController(model, boardView, tools);

        main.add(boardView, BorderLayout.CENTER);
        main.add(tools, BorderLayout.SOUTH);

        frame.getContentPane().add(main);
        frame.setSize(new Dimension(300, 300));

        frame.setVisible(true);
    }

}
