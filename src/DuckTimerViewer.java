import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class DuckTimerViewer extends JFrame implements KeyListener{
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;

    private Image image;
    private DuckTimer d;

    private JButton start;

    private ImageIcon icon;
    private int screenStatus;

    public DuckTimerViewer(DuckTimer d){
        this.d = d;

        screenStatus = 0;

        /*icon = new ImageIcon("Resources/Opening.png");
        start = new JButton(icon);
        this.add(start);
        start.setLocation(250, 250);
        */

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Duck Timer");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        addKeyListener(this);
    }

    public void setScreenStatus(int screenStatus) {
        this.screenStatus = screenStatus;
    }

    public void paint (Graphics g){
        if(screenStatus == 0){
            // Opening Screen
            image = new ImageIcon("Resources/Opening.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        }
        else if (screenStatus == 1) {
            //timer set up screen
            //  image = new ImageIcon().getImage();
            // g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.setColor(Color.orange);
            g.drawRect(0, 22, 255, 255);

        }
        else if (screenStatus == 2) {
            // timer screen
            //  image = new ImageIcon().getImage();
            // g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            d.createDucks();
            d.startClock();


        }
        else if (screenStatus == 3) {

        }
        else{
            screenStatus = 0;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_N) {
            screenStatus++;
        }

        repaint();
    }
}
