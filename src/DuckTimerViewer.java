import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class DuckTimerViewer extends JFrame implements KeyListener, ActionListener {
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;

    private static final String START_GAME = "start";

    private Image image;
    private DuckTimer d;

    private JButton start;

    private ImageIcon icon;
    private int screenStatus;

    public DuckTimerViewer(DuckTimer d){
        this.d = d;

        screenStatus = 0;

        //icon = new ImageIcon("Resources/Opening.png");

        /*start = new JButton("Start Game");
        start.setBounds(460, 400, 180,60);
        start.setActionCommand(START_GAME);
        start.addActionListener(this);

         */
        //this.add(start);

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

            start = new JButton("Start Game");
            start.setBounds(460, 400, 180,60);
            start.setActionCommand(START_GAME);
            start.addActionListener(this);
            this.add(start);
        }
        else if (screenStatus == 1) {
            start.setVisible(false);
            g.setColor(Color.orange);
            g.drawRect(0, 22, 255, 255);
            g.fillRect(0, 22, 255, 255);

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

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(START_GAME)) {
            start.setVisible(false);
            this.remove(start);
            screenStatus = 1;
            repaint();
        }
    }
}
