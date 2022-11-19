import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    List<iPoint> snakePoints;
    int xDir, yDir;
    boolean isMoving, elongate;
    final int STARTSIZE = 20, STARTX  = 150, STARTY = 150;

    //Constructor
    //Makes og snake
    public Snake (){
        snakePoints = new ArrayList<iPoint>();
        xDir = 0;
        yDir = 0;
        isMoving = false;
        elongate = false;
        snakePoints.add(new iPoint(STARTX, STARTY));
        for(int i = 1; i < STARTSIZE; i++){
            snakePoints.add(new iPoint(STARTX - i * 4, STARTY));
        }
    }
    //Color of snake
    public void draw(Graphics g){
        g.setColor(Color.white);
        for(iPoint p: snakePoints){
            g.fillRect(p.getX(), p.getY(), 4, 4);
        }
    }
    //Moves snake, loops over every point in the snake and update it
    public void move(){
        if(isMoving){
            iPoint temp = snakePoints.get(0);
            iPoint last = snakePoints.get(snakePoints.size() - 1);
            iPoint newStart = new iPoint(temp.getX() + xDir * 4, temp.getY() + yDir * 4 );
            for(int i = snakePoints.size() - 1; i >= 1; i--){
                snakePoints.set(i, snakePoints.get(i -1));
            }
            snakePoints.set(0, newStart);
            if(elongate){
                snakePoints.add(last);
                elongate = false;
            }
        }
    }

    //Checks to see if snake runs into itself and ends game
    public boolean snakeSnakeCollision() {

        int headX = this.getHeadX();
        int headY = this.getHeadY();
        for (int i = 1; i < snakePoints.size(); i++) {
            int xi = snakePoints.get(i).getX();
            int yi = snakePoints.get(i).getY();

            if (xi == headX && yi == headY) {
                return true;

            }
        }
        return false;
    }
    public boolean isMoving(){
        return isMoving;
    }

    public void setIsMoving(boolean b){
        isMoving = b;
    }

    public int getxDir(){
        return xDir;
    }
    public int getyDir(){
        return yDir;
    }
    public void setxDirt(int x){
        xDir = x;
    }
    public void setyDirt(int y){
        yDir = y;
    }
    public int getHeadX(){
        //return snakePoints.get(snakePoints.size() -1).getX();
        return snakePoints.get(0).getX();

    }
    public int getHeadY(){
       //return snakePoints.get(snakePoints.size() -1).getY();
       return snakePoints.get(0).getY();
    }

    public void setElongate(boolean b){
        elongate = b;
    }

}
