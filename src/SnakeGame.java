import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class SnakeGame extends JPanel{
    int boardWidht;
    int boardHeight;

    SnakeGame(int boardWidht, int boardHeight) {
        this.boardWidht = boardWidht;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidht, this.boardHeight));
        setBackground(Color.black);
    }
}
