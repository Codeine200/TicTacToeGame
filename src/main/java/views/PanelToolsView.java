package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Василий on 15.06.2015.
 */
public class PanelToolsView extends JPanel {
    private JButton startGame = new JButton("Start Game");
    private JLabel message = new JLabel();

    public  PanelToolsView() {
        setLayout(new GridLayout(0, 2));
        startGame.setPreferredSize(new Dimension(50, 30));
        startGame.setSize(new Dimension(50, 30));
        add(startGame);
        add(message);
    }

    public void setMessage(String text) {
        message.setText(text);
    }

    public void addButtonListener(ActionListener listener) {
        startGame.addActionListener(listener);
    }
}
