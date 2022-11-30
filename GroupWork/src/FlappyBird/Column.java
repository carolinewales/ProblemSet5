package FlappyBird;

import java.awt.*;

public class Column extends Rectangle {
    public boolean hasCoin;
    public Column (int x, int y, int width, int height){
        super(x,y,width,height);
        if (Math.random() < 0.5 && y == 0){
            hasCoin = true;
        }
        else {
            hasCoin = false;
        }
    }

    public void paintColumn (Graphics g){
        g.setColor(Color.green.darker().darker());
        g.fillRect(this.x, this.y, this.width, this.height);

        if (hasCoin){
            g.setColor(Color.yellow);
            int xc = x + width / 4;
            int yc = y + height + 65;
            g.fillOval(xc, yc, 50, 50);
        }
    }

    public Rectangle getCoin(){
        int xc = x + width / 4;
        int yc = y + height + 65;
        return new Rectangle(xc, yc, 50, 50);
    }

    public void eraseCoin() {
        hasCoin = false;
    }
}
