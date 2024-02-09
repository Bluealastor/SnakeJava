import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class SnakeGame extends JPanel implements ActionListener, KeyListener{

    public void move() {
        // Snake Head
        snakeHead.x += positionX;
        snakeHead.y += positionY;
    };
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }


    // Funzione ascolto Tasti
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            positionX = 0;
            positionY = -1;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            positionX = 0;
            positionY = 1;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            positionX = -1;
            positionY = 0;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            positionX = 1;
            positionY = 0;
        }
    }

    /* NON Toccare */
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    /* NON Toccare */

    private class Tile {
        int x;
        int y;

        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    // Attributi
    int boardWidht;
    int boardHeight;
    int tileSize = 25;

    // SNAKE
    Tile snakeHead;

    // FOOD
    Tile food;
    Random random;

    // logica gioco
    Timer gameLoop;
    int positionX;
    int positionY;

    // costruttore della Classe
    SnakeGame(int boardWidht, int boardHeight) {
        // inizzazione degli attributi
        this.boardWidht = boardWidht;
        this.boardHeight = boardHeight;

        // Dimensione e colore del board games
        setPreferredSize(new Dimension(this.boardWidht, this.boardHeight));
        setBackground(Color.black);

        // Funzionamento ascolto tasti
        addKeyListener(this);
        setFocusable(true);

        // SNAKE posizione
        snakeHead = new Tile(5, 5);

        // FOOD posizione
        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        positionX = 0;
        positionY = 0;

        // aggiornamento posizione
        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
      draw(g);
    }

    public void draw(Graphics g){
        // GRIGLIA
        for (int grid = 0; grid < boardWidht/tileSize; grid++){
            g.drawLine(grid* tileSize, 0, grid * tileSize, boardHeight);
            g.drawLine(0, grid * tileSize,  boardWidht, grid * tileSize);
        }

        // impostazioni posizione Snake
        g.setColor(Color.blue);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);

        // impostazioni posizione Food
        g.setColor(Color.yellow);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize );

    }

    //Funzione per posizionamento random food
    public void placeFood(){
        food.x = random.nextInt(boardWidht/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }
}
