import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends JFrame implements Runnable, KeyListener {

    Graphics g;
    Image img;
    Snake snake;
    Thread thread;
    boolean gameOver;
    Token token;

    static int WIDTH = 400;

    static int HEIGHT = 400;

    public SnakeGame() {
        snake = new Snake();
        this.setSize(WIDTH, HEIGHT);
        gameOver = false;
        this.setTitle("Snake Game");

        this.addKeyListener(this);
        token = new Token(snake);
        this.setVisible(true);  // Needed here for Graphics to work properly
        thread = new Thread(this);
        thread.start();

    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        if(!gameOver){
            snake.draw(g);
            token.draw(g);
        }
        else{
            g.setColor(Color.RED);
            g.drawString("Game Over", 180, 150);
            g.drawString("Score: " + token.getScore(), 180, 170);
        }
        g.drawImage(img, 0, 0, null);
    }

    public void update(Graphics g) {

    }

    public void repaint(Graphics g) {

    }

    public void run() {
        while(true){
            if(!gameOver) {
                snake.move();
                this.checkGameOver();
                token.snakeCollision();
            }
                this.repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void checkGameOver(){
        if(snake.getX() < 0 || snake.getX() > 396){
            gameOver = true;
        }
        if(snake.getY() < 0 || snake.getY() > 396){
            gameOver = true;
        }
        if(snake.snakeCollision()){
            gameOver = true;
        }
    }

    public void keyPressed(KeyEvent e){
        if(!snake.isMoving()){
            if(e.getKeyCode() == KeyEvent.VK_UP ||
                    e.getKeyCode() == KeyEvent.VK_RIGHT  ||
                    e.getKeyCode() == KeyEvent.VK_DOWN){
                snake.setIsMoving(true);

            }
        }
        if(e.getKeyCode () == KeyEvent.VK_UP){
            if(snake.getyDir() != 1){
                snake.setyDirt(-1);
                snake.setxDirt(0);
            }
        }
        if(e.getKeyCode () == KeyEvent.VK_DOWN){
            if(snake.getyDir() != -1){
                snake.setyDirt(1);
                snake.setxDirt(0);
            }
        }
        if(e.getKeyCode () == KeyEvent.VK_LEFT){
            if(snake.getxDir() != 1){
                snake.setxDirt(-1);
                snake.setyDirt(0);
            }
        }
        if(e.getKeyCode () == KeyEvent.VK_RIGHT){
            if(snake.getxDir() != -1){
                snake.setxDirt(1);
                snake.setyDirt(0);
            }
        }

    }

    public void keyReleased(KeyEvent arg0){

    }

    public void keyTyped(KeyEvent arg0){

    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
