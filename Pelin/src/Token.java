import java.awt.*;

public class Token {

    private int x, y, score;
    private Snake snake;

    //Constructor, generating random x and y coordinates for intial token placement
    public Token(Snake s) {
        x = (int) (Math.random() * 395);
        y = (int) (Math.random() * 395);
        snake = s;
    }

    //Changes position of token after snake eats them
    public void changePosition() {
        x = (int) (Math.random() * 395);
        y = (int) (Math.random() * 395);
    }

    public int getScore() {
        return score;
    }

    //Color of tokens
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 6, 6);
    }

    //Checks if snake ate token and then updates score and elongates snake
    public boolean snakeTokenCollision() {
        int snakeX = snake.getHeadX() + 2;
        int snakeY = snake.getHeadY() + 2;
        if(snakeX >= x -1 && snakeX <= (x+7)){
            if(snakeY >= y -1 && snakeY <= (y+7)){
                changePosition();
                score++;
                snake.setElongate(true);
                return true;
            }
        }
        return false;
    }

}
