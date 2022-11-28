package FlappyBird;

import java.awt.*;

public class Coin {

    private int x, y, score;
    private FlappyBird fb;


    public Coin(FlappyBird fb) {
        x = (int) (Math.random() * 395);
        y = (int) (Math.random() * 395);
        this.fb = fb;
    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(x, y, 8, 8);
    }


    public void changeCoinPosition() {
        x = (int) (Math.random() * 395);
        y = (int) (Math.random() * 395);
    }

    public int getScore() {
        return score;
    }

    public boolean birdTokenCollision() {
//        if(bird.intersects(coin)){
        return true;

    }
}

