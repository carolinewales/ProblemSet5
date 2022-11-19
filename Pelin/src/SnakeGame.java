/**********************************************************************
 * @file SnakeGame.java
 * @brief: I worked with professor pauca on some errors I had friday during class so my code would compiled
 * and emailed him about submitting the next morning so my game worked completely
 * @author Pelin Blanton
 * @date: 11/19/2022
 * @acknowledgement: none
 ***********************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends JFrame implements Runnable, KeyListener {

    Graphics g;
    Image img;
    Snake snake;
    Thread thread;
    boolean gameOver = false;
    Token token;

    static int WIDTH = 400;

    static int HEIGHT = 400;
    //Runs whole game- Constructor
    public SnakeGame() {

        snake = new Snake();
        token = new Token(snake);

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Snake Game");
        this.addKeyListener(this);
        this.setVisible(true);  // Needed here for Graphics to work properly


        thread = new Thread(this);
        thread.start();

    }

    //Paints canvas and checks for game OVER
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        //System.out.println(gameOver);
        if(!gameOver){
            snake.draw(g);
            token.draw(g);
        }
        //Prints game over
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
    //main game loop
    public void run() {
        while(true){
            if(!gameOver) {
                snake.move();
                this.checkGameOver();
                token.snakeTokenCollision();
           }
            this.repaint();
            try {
                //Controls speed of game
                Thread.sleep(60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    //Checks for game over based on out of bounds or snake collision
    public void checkGameOver(){
        if(snake.getHeadX() < 0 || snake.getHeadX() > 396){
            gameOver = true;
        }
        if(snake.getHeadY() < 0 || snake.getHeadY() > 396){
            gameOver = true;
        }
        if(snake.snakeSnakeCollision()){
            gameOver = true;
        }
    }

    //Handle key press
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

    //Overrides base class and doesn't do anything
    public void keyReleased(KeyEvent e){

    }

    public void keyTyped(KeyEvent e){

    }

    //Main method so program can run and exits when game is done
    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
