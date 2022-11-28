/**********************************************************************
 * FLAPPYBIRD.java
 * N/A
 * CAROLINE WALES
 * 11/18/22
 * JARYT BUSTARD
 ***********************************************************************/
package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird implements ActionListener, MouseListener, KeyListener {
    public static FlappyBird fb;
    public Renderer renderer;
    public Rectangle bird;
    public ArrayList<Rectangle> columns;
    public int ticks;
    public int yMotion;
    public int score = 0;
    public boolean gameOver;
    public boolean started;
    public int coin = 0;
    public Random rand = new Random();


    public FlappyBird(){
        // Creates window, makes sure user can't resize it
        JFrame jframe = new JFrame();
        Timer timer = new Timer (20, this);

        renderer = new Renderer();

        jframe.setTitle("Flappy Bird!");
        jframe.add(renderer);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setSize(800, 800);
        jframe.setResizable(false);
        jframe.setVisible(true);

        // Creates and positions bird
        bird = new Rectangle(400 - 10, 400 - 10, 20, 20);

        // Creates columns at start of game
        columns = new ArrayList<>();
        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }

    // Creates starting columns on screen
    public void addColumn(boolean start){
        // Sets perimeters for how tall and short columns can be
        int space = 300;
        int width = 100;
        int height = 50 + rand.nextInt(300);

        if (start) {
            columns.add(new Rectangle(800 + width + columns.size() * 300, 800 - height - 120, width, height));
            columns.add(new Rectangle(800 + width + (columns.size() - 1) * 300, 0, width, 800 - height - space));
        }
        else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, 800 - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, 800 - height - space));
        }
    }

    // Aesthetics for columns
    public void paintColumn (Graphics g, Rectangle column){
        g.setColor(Color.green.darker().darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    // Uses mouse/keyboard input to move bird
    public void jump(){
        //Starting the game
        if (gameOver){
            gameOver = false;

            bird = new Rectangle(400 - 10, 400 - 10, 20, 20);
            columns.clear();
            yMotion = 0;
            score = 0;

            columns = new ArrayList<>();
            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);
        }

        if (!started){
            started = true;
        }
        else if (!gameOver){
            if (yMotion > 0){
                yMotion = 0;
            }

            yMotion -= 10;
        }
    }

    // Puts background and moving column components on screen
    @Override
    public void actionPerformed(ActionEvent e) {
        int speed = 10;

        ticks++;

        if (started){
            for (int i = 0; i < columns.size(); i++){
                Rectangle column = columns.get(i);
                column.x -= speed;
            }

            if (ticks % 2 == 0 && yMotion < 15){
                yMotion += 2;
            }

            // Makes columns appear infinitely
            for (int i = 0; i < columns.size(); i++){
                Rectangle column = columns.get(i);
                if (column.x + column.width < 0){
                    columns.remove(column);
                    if (column.y == 0) {
                        addColumn(false);
                    }
                }
            }

            bird.y += yMotion;

            // If the bird successfully passes through columns
            for (Rectangle column : columns){
                if ((column.y == 0) && (bird.x + bird.width / 2 > column.x + column.width / 2 - 10) && (bird.x + bird.width / 2 < column.x + column.width / 2 + 10)){
                    score++;
                }
                //if(birdTokenCollision()){
                   // coin++;
               // }
                // If the bird hits a column
                if (column.intersects(bird)){
                    gameOver = true;

                    // How the "dead" bird moves
                    if (bird.x <= column.x){
                        bird.x = column.x - bird.width;
                    }
                    else if (column.y != 0){
                        bird.y = column.y - bird.height;
                    }
                    else if (bird.y < column.height){
                        bird.y = column.height;
                    }
                }
            }

            // Game over if bird goes out of bounds
            if (bird.y > (800 - 120) || bird.y < 0){
                gameOver = true;
            }

            // Sets bird.y if bird stays in bounds
            if (bird.y + yMotion >= 800 - 120){
                bird.y = 800 - 150 - bird.height;
            }
        }

        renderer.repaint();
    }

    // Draws components of background, columns, and bird
    public void repaint(Graphics g) {
        // Sky
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 800, 800);

        // Dirt
        g.setColor(new Color(254, 204, 133));
        g.fillRect(0, 800 - 150, 800, 150);

        // Grass
        g.setColor(new Color(113, 197, 98));
        g.fillRect(0, 800 - 150, 800, 30);


        // Bird
        g.setColor(new Color (231, 84, 128));
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        // Clouds
        g.setColor(Color.white);


        // Columns
        for (Rectangle column : columns){
            paintColumn(g, column);
        }

        // Setting font and font color
        g.setColor(Color.white);
        g.setFont(new Font ("Arial", Font.BOLD, 100));

        //Message at start of game
        if (!started){
            g.drawString("Click to Start!", 75, 400);
        }

        // Display score
        g.setFont(new Font ("Arial", Font.BOLD, 50));
        if (!gameOver && started){
            g.drawString("Score: ", 5, 50);
            g.drawString(String.valueOf(score), 170, 50);
        }

        // Message if game ends
        g.setFont(new Font ("Arial", Font.BOLD, 100));
        if (gameOver) {
            g.drawString("Game Over!", 100, 400);
        }
    }

    public static void main(String[] args) {
        fb = new FlappyBird();
    }

    // Listeners that take user input
    @Override
    public void mouseClicked(MouseEvent e) {
        jump();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            jump();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
}