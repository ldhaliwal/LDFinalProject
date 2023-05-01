import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DuckTimer implements ActionListener {
    private static final int SLEEP_TIME = 100;
    private static final int MIN_TO_MILLISECONDS = 60000;
    private DuckTimerViewer window;
    private Timer clock;

    private int timeLeft;
    private int minutes;
    private int seconds;

    private Image[] duckImages;
    private Duck[] ducks;

    private int numDucks;

    public DuckTimer(){
        window = new DuckTimerViewer(this);

        duckImages = new Image[21];

        for(int i = 0; i < 21; i++){
            duckImages[i] = new ImageIcon("Resources/" + (i + 1) + ".png").getImage();
        }
    }

    public void actionPerformed(ActionEvent e) {
        timeLeft -= SLEEP_TIME;
        minutes = timeLeft/MIN_TO_MILLISECONDS;
        seconds = (timeLeft - (minutes * MIN_TO_MILLISECONDS)) / 1000;

        if (timeLeft <= 0){
            timeOver();
        }
        window.repaint();
    }

    public void setTimeLeft(){
        //setNumDucks(Integer.parseInt(JOptionPane.showInputDialog("How many ducks do you want to race? (max 21)")));
        int time = Integer.parseInt(JOptionPane.showInputDialog("How long do you want your timer to last? (in minutes)"));
        timeLeft = time * MIN_TO_MILLISECONDS;
    }

    public void startClock(){
        clock = new Timer(SLEEP_TIME, this);
        clock.start();
    }

    public void setNumDucks(){
        int num = Integer.parseInt(JOptionPane.showInputDialog("How many ducks do you want to race? (max 21)"));
        numDucks = num;
    }

    public int getNumDucks(){
        return numDucks;
    }

    public Duck[] getDucks() {
        return ducks;
    }

    public void createDucks(){
        ducks = new Duck[numDucks];
        for (int i = 0; i < numDucks; i++){
            Duck duck = new Duck(i, window);
            ducks[i] = duck;
            duck.setDuckImage(duckImages[i]);
        }

        int winDuck = (int) (Math.random() * numDucks);
        ducks[winDuck].setWinningDuck(true);

        window.setDucks(ducks);
        window.setScreenStatus(2);
    }

    public void timeOver(){
        clock.stop();
        window.setScreenStatus(3);
    }

    public static void main(String[] args) {
        //runs the game
        DuckTimer d = new DuckTimer();
        d.run();
    }

    public void run(){
        window.repaint();
    }
}
