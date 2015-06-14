package views;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.EventListener;

/**
 * Created by Василий on 13.06.2015.
 */
public class BoardView extends JPanel {

    private JButton[][] squares;
    private int size;

    public BoardView(int size) {

        this.size = size;
        setLayout(new GridLayout(0, size));
        squares = new JButton[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                add(createSquare(i, j));
    }

    private JComponent createSquare(int row, int column) {
        squares[row][column] = new JButton();
        squares[row][column].setName(row + ":" + column);
        squares[row][column].setFocusPainted(false);
        squares[row][column].setOpaque(false);
        return squares[row][column];
    }

    public void addSquareActionListener(ActionListener listener) {
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++) {
                squares[i][j].addActionListener(listener);
            }
    }

    public void setIcon(String pathImage, int row, int column) {
        if(row >= 0 && row < size && column >= 0 && column < size) {
            squares[row][column].setIcon(new ImageIcon(pathImage));
        }
    }


}
