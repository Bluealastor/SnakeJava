import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            gameLoop.stop();
        }
    }

    // Funzione ascolto Tasti
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && positionY != 1){
            positionX = 0;
            positionY = -1;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && positionY != -1) {
            positionX = 0;
            positionY = 1;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && positionX != 1){
            positionX = -1;
            positionY = 0;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT && positionX != -1){
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
    ArrayList<Tile> snakeBody;

    // FOOD
    Tile food;
    Random random;

    // logica gioco
    Timer gameLoop;
    int positionX;
    int positionY;
    boolean gameOver = false;

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
        snakeBody = new ArrayList<Tile>();

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
       /*
       for (int grid = 0; grid < boardWidht/tileSize; grid++){
            g.drawLine(grid* tileSize, 0, grid * tileSize, boardHeight);
            g.drawLine(0, grid * tileSize,  boardWidht, grid * tileSize);
        }
        */

        // impostazioni posizione Food
        g.setColor(Color.yellow);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize,true );

        // impostazioni posizione Snake
        g.setColor(Color.blue);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);


        // corpo Snake
        for (int i= 0; i < snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize,tileSize, true);
        }

        // punteggio
        g.setFont(new Font("arial",Font.PLAIN,16));
        if(gameOver){
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }else {
            g.setColor(Color.green);
            g.drawString("Punteggio: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }

    }



    //Funzione per posizionamento random food
    public void placeFood(){
        food.x = random.nextInt(boardWidht/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {
        // eat Food
        if (collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Corpo Snake
        for (int i = snakeBody.size()-1; i >= 0; i--){
            Tile snakePart = snakeBody.get(i);
            if(i == 0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }else {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // Snake Head
        snakeHead.x += positionX;
        snakeHead.y += positionY;

        // end Game
        for( int i=0; i< snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            //crush snake head
            if(collision(snakeHead, snakePart)){
                gameOver = true;
            }
        }

        if(snakeHead.x*tileSize <0 || snakeHead.x*tileSize > boardWidht || snakeHead.y*tileSize <0 || snakeHead.y*tileSize > boardHeight){
            gameOver = true;
        }

    };
}
