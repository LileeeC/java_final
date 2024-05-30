import javax.swing.*;
import java.awt.*;

public class ShowInventory extends JFrame {
    public static void main(String[] args) { 
        JFrame frame = new JFrame();
        frame.setTitle("店面經營系統");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}