//Class with int points, because java class uses doubles
public class iPoint {
        private int x, y;

        public iPoint() {
            x = 0;
            y = 0;
        }

        public iPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

