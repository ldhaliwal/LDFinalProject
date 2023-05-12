import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DuckTimerViewer extends JFrame implements ActionListener {
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;

    private final int START_X = 450;
    private final int START_Y = 250;
    private final int START_WIDTH = 300;
    private final int START_HEIGHT = 100;

    private final int CLOCK_X = 380;
    private final int CLOCK_Y = 100;
    private final int CLOCK_WIDTH = 425;
    private final int CLOCK_HEIGHT = 200;

    private final int MINUTES_X = 380;
    private final int MINUTES_Y = 260;

    private final int SECONDS_X = 605;
    private final int SECONDS_Y = 260;

    private final int LEADING_X_1 = 35;
    private final int LEADING_X_2 = 830;
    private final int LEADING_Y = 200;

    private final int TIME_UP_X = 400;
    private final int TIME_UP_Y = 150;

    private final int WIN_DUCK_X = 200;
    private final int WIN_DUCK_Y = 370;


    private static final String START_GAME = "start";

    private Image image;

    private Duck[] ducks;
    private DuckTimer d;

    private JButton start;

    private int screenStatus;

    public DuckTimerViewer(DuckTimer d){
        this.d = d;
        screenStatus = 0;

        start = new JButton("Start");
        start.setBounds(START_X, START_Y, START_WIDTH,START_HEIGHT);
        start.setActionCommand(START_GAME);
        start.addActionListener(this);

        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Duck Timer");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
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
            // Draws the background
            image = new ImageIcon("Resources/Opening.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            this.getContentPane().add(start);
        }
        else if (screenStatus == 1) {
            // Draws the background
            image = new ImageIcon("Resources/Opening.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            for(int i = d.getNumDucks() - 1; i >= 0; i--){
                image = ducks[i].getDuckImage();
                int x = ducks[i].getX();
                int y = ducks[i].getY();
                g.drawImage(image, x, (y - (image.getHeight(this)/5)), image.getWidth(this)/5, image.getHeight(this)/5, this);
            }
            screenStatus = 2;
            d.startClock();
        }
        else if (screenStatus == 2) {
            // Draws the background
            image = new ImageIcon("Resources/RaceBackground.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            // Draws ducks
            for(int i = d.getNumDucks() - 1; i >= 0; i--){
                image = ducks[i].getDuckImage();
                int x = ducks[i].getX();
                int y = ducks[i].getY();
                g.drawImage(image, x, (y - (image.getHeight(this)/5)), image.getWidth(this)/5, image.getHeight(this)/5, this);
            }

            // Draws the clock
            image = new ImageIcon("Resources/ClockBackground.png").getImage();
        g.drawImage(image, CLOCK_X, CLOCK_Y, CLOCK_WIDTH, CLOCK_HEIGHT, this);

            // Draws the numbers on the clock
            g.setFont(new Font("SansSerif", Font.BOLD, 150));
            if(d.getMinutes() < 10){
                g.drawString("0"+String.valueOf(d.getMinutes()), MINUTES_X, MINUTES_Y);
            }
            else{
                g.drawString(String.valueOf(d.getMinutes()), MINUTES_X, MINUTES_Y);
            }

            if(d.getSeconds() < 10){
                g.drawString("0"+String.valueOf(d.getSeconds()), SECONDS_X, SECONDS_Y);
            }
            else{
                g.drawString(String.valueOf(d.getSeconds()), SECONDS_X, SECONDS_Y);
            }

            // Finds the winning duck
            Duck leader = d.getLeader();

            //Draws the winning duck's number and lap count
            g.setFont(new Font("SansSerif", Font.BOLD, 25));
            g.drawString("Winning duck: #" + (leader.getNumber() + 1), LEADING_X_1, LEADING_Y);
            g.drawString("Duck #" + (leader.getNumber() + 1) + " has made " + leader.getLaps() + " laps", LEADING_X_2, LEADING_Y);
        }
        else if (screenStatus == 3) {
            // Draws the background
            image = new ImageIcon("Resources/OverBackground.png").getImage();
            g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            //  Finds the winning duck
            String winningDuck = String.valueOf(d.getLeader().getNumber() + 1);

            // Draws the ending message and which duck won
            g.setFont(new Font("SansSerif", Font.BOLD, 70));
            g.setColor(Color.black);
            g.drawString("Time is up!", TIME_UP_X, TIME_UP_Y);
            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            g.drawString("The winning duck is duck number " + winningDuck + "!", WIN_DUCK_X, WIN_DUCK_Y);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(START_GAME)) {
            // Makes the button invisible
            start.setVisible(false);
            revalidate();

            d.setTimeLeft();
            d.setNumDucks();
            d.createDucks();

            repaint();
        }
    }
}
