import com.sun.jdi.IntegerValue;
import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;


public class DuckTimerViewer extends JFrame implements KeyListener, ActionListener {
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;

    private static final String START_GAME = "start";

    private Image image;

    private Duck[] ducks;
    private DuckTimer d;

    private JButton start;

    private int screenStatus;

    public DuckTimerViewer(DuckTimer d){
        this.d = d;
        screenStatus = 0;

        start = new JButton("Start Game");
        start.setBounds(460, 400, 180,60);
        start.setActionCommand(START_GAME);
        start.addActionListener(this);
        //this.getContentPane().add(start);

        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Duck Timer");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        addKeyListener(this);
    }

    public void setScreenStatus(int screenStatus) {
        this.screenStatus = screenStatus;
    }

    public DuckTimer getD() {
        return d;
    }

    public void setDucks(Duck[] ducks) {
        this.ducks = ducks;
    }

    public void paint (Graphics g){
        if(screenStatus == 0){
            // Opening Screen
            image = new ImageIcon("Resources/Opening.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            this.getContentPane().add(start);
        }
        else if (screenStatus == 1) {
            image = new ImageIcon("Resources/Opening.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            g.setColor(Color.gray);
            g.drawRect(0, 22, 255, 255);
            g.fillRect(0, 22, 255, 255);
        }
        else if (screenStatus == 2) {
            // timer screen
            image = new ImageIcon("Resources/Opening.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            g.setColor(Color.blue);
            g.drawRect(0, 22, 255, 255);
            g.fillRect(0, 22, 255, 255);

            for(int i = d.getNumDucks() - 1; i >= 0; i--){
                image = ducks[i].getDuckImage();
                int x = ducks[i].getX();
                int y = ducks[i].getY();
                g.drawImage(image, x, (y - (image.getHeight(this)/5)), image.getWidth(this)/5, image.getHeight(this)/5, this);
            }
            screenStatus = 3;
            d.startClock();
        }
        else if (screenStatus == 3) {
            image = new ImageIcon("Resources/Opening.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            g.setColor(Color.black);
            g.drawRect(0, 22, 255, 255);
            g.fillRect(0, 22, 255, 255);

            // Draws ducks
            for(int i = d.getNumDucks() - 1; i >= 0; i--){
                image = ducks[i].getDuckImage();
                int x = ducks[i].getX();
                int y = ducks[i].getY();
                g.drawImage(image, x, (y - (image.getHeight(this)/5)), image.getWidth(this)/5, image.getHeight(this)/5, this);
            }

            // Draws clock
            image = new ImageIcon("Resources/ClockBackground.png").getImage();
            g.drawImage(image, 400, 100, 420, 200, this);

            g.setFont(new Font("SansSerif", Font.BOLD, 150));
            if(d.getMinutes() < 10){
                g.drawString("0"+String.valueOf(d.getMinutes()), 400, 260);
            }
            else{
                g.drawString(String.valueOf(d.getMinutes()), 400, 260);
            }

            if(d.getSeconds() < 10){
                g.drawString("0"+String.valueOf(d.getSeconds()), 625, 260);
            }
            else{
                g.drawString(String.valueOf(d.getSeconds()), 625, 260);
            }


        }
        else if (screenStatus == 4) {
            image = new ImageIcon("Resources/Opening.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            g.setColor(Color.red);
            g.drawRect(0, 22, 255, 255);
            g.fillRect(0, 22, 255, 255);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        int keyCode = e.getKeyCode();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(START_GAME)) {
            //this.getContentPane().remove(start);
            start.setVisible(false);
            screenStatus = 1;
            revalidate();

            d.setTimeLeft();
            d.setNumDucks();
            d.createDucks();

            repaint();
        }

    }
}
