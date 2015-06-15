package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Василий on 15.06.2015.
 */
public class PanelToolsView extends JPanel {
    private JButton startGame = new JButton("Start");
    private JLabel message = new JLabel();
    private JTextField n = new JTextField(2);

    public  PanelToolsView() {
        setLayout(new GridLayout(0, 4));
        add(startGame);
        add(message);
        add(new JLabel("N:", SwingConstants.RIGHT));
        add(n);
    }

    public void setMessage(String text) {
        message.setText(text);
    }

    public void addButtonListener(ActionListener listener) {
        startGame.addActionListener(listener);
    }

    public void setN(int n) {
        this.n.setText(String.valueOf(n));
    }

    public int getN() {
        String text = n.getText();
        int n = 0;
        try {
            n = Integer.valueOf(text);
        } catch (NumberFormatException m) {
            n = 0;
        }

        return n;
    }
}
