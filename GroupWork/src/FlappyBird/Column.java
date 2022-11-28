package FlappyBird;

import java.awt.*;
import java.util.ArrayList;

public class Column extends Rectangle {
    public boolean hasCoin;
    public Column (int x, int y, int width, int height){
        super(x,y,width,height);
        // How to pass columns parameter?
        if (Math.random() < 0.5 && x == 800 + width + (columns.size() - 1) * 300 && y == 0 && height == 800 - height - 300){
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
}
