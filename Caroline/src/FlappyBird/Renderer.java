package FlappyBird;
import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FlappyBird.fb.repaint(g);
    }
}
