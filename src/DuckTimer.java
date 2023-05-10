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

        updateDucks();
        window.repaint();
    }

    public void setTimeLeft(){
        int time = 0;
        while(time < 1){
            time = Integer.parseInt(JOptionPane.showInputDialog("How long do you want your timer to last? (in minutes)"));
        }
        timeLeft = time * MIN_TO_MILLISECONDS;
    }

    public void startClock(){
        clock = new Timer(SLEEP_TIME, this);
        clock.start();
    }

    public void setNumDucks(){
        int num = 0;
        while(num < 1 || num > 21){
            num = Integer.parseInt(JOptionPane.showInputDialog("How many ducks do you want to race? (max 21)"));
        }
        numDucks = num;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getNumDucks(){
        return numDucks;
    }


    public void createDucks(){
        ducks = new Duck[numDucks];

        for (int i = 0; i < numDucks; i++){
            Duck duck = new Duck(i, window);
            ducks[i] = duck;
            duck.setDuckImage(duckImages[i]);

            // Initializes the duck's speed
            int randomSpeed = (int) (Math.random() * 5) + 1;
            duck.setSpeed(randomSpeed);
        }

        window.setDucks(ducks);
        window.setScreenStatus(2);
    }

    public void updateDucks(){
        for(Duck d : ducks){
            // Updates the speed of each duck
            int randomSpeed = (int) (Math.random() * 5) - 2;
            d.setSpeed(d.getSpeed() + randomSpeed);

            if((d.getX() < 0 && d.getSpeed() < 0) || d.getSpeed() > 20 || d.getSpeed() < -5){
                d.setSpeed(5);
            }

            // Updates the x value of each duck
            d.setX(d.getX() + d.getSpeed());
            if(d.getX() >= 1200){
                d.setX(0);
                d.setLaps(d.getLaps() + 1);
            }
        }
    }

    public Duck getLeader(){
        int topDuck = 0;
        int topLaps = Integer.MIN_VALUE;
        for(int i = 0; i < ducks.length; i++){
            if(ducks[i].getLaps() > topLaps){
                topDuck = i;
                topLaps = ducks[i].getLaps();
            }
            if(ducks[i].getLaps() == topLaps && ducks[i].getX() > ducks[topDuck].getX()){
                topDuck = i;
                topLaps = ducks[i].getLaps();
            }
        }

        return ducks[topDuck];
    }

    public void timeOver(){
        clock.stop();
        window.setScreenStatus(4);
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
