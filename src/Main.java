import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        int boardWidht = 600;
        int boardHeight = boardWidht;

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardWidht, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame = new SnakeGame(boardWidht, boardHeight);
        frame.add(snakeGame);
    }

}