import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DuckTimer implements ActionListener {
    private static final int SLEEP_TIME = 100;
    private static final int MIN_TO_MILLISECONDS = 60000;

    private final int WINDOW_WIDTH = 1200;

    private final int MAX_SPEED = 20;
    private static final int TOTAL_DUCKS = 21;
    private DuckTimerViewer window;
    private Timer clock;

    private int timeLeft;
    private int minutes;
    private int seconds;

    private Image[] duckImages;
    private Duck[] ducks;

    private int numDucks;

    public DuckTimer(){
        // Creates a new window
        window = new DuckTimerViewer(this);

        duckImages = new Image[TOTAL_DUCKS];

        // Creates an array of all possible images for the ducks
        for(int i = 0; i < TOTAL_DUCKS; i++){
            duckImages[i] = new ImageIcon("Resources/" + (i + 1) + ".png").getImage();
        }
    }

    public void actionPerformed(ActionEvent e) {
        // Updates the value of all time variables
        timeLeft -= SLEEP_TIME;
        minutes = timeLeft/MIN_TO_MILLISECONDS;
        seconds = (timeLeft - (minutes * MIN_TO_MILLISECONDS)) / 1000;

        // Calls timeOver() if the time is ended
        if (timeLeft <= 0){
            timeOver();
        }

        updateDucks();
        window.repaint();
    }

    public void setTimeLeft(){
        int time = 0;
        // Takes in user input for the length of the timer
        while(time < 1){
            time = Integer.parseInt(JOptionPane.showInputDialog("How long do you want your timer to last? (in minutes)"));
        }
        timeLeft = time * MIN_TO_MILLISECONDS;
    }

    public void startClock(){
        // Creates and starts the timer
        clock = new Timer(SLEEP_TIME, this);
        clock.start();
    }

    public void setNumDucks(){
        int num = 0;
        // Takes in user input for the number of ducks to race
        while(num < 1 || num > TOTAL_DUCKS){
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
            // Creates each duck and assigns its image
            Duck duck = new Duck(i, window);
            ducks[i] = duck;
            duck.setDuckImage(duckImages[i]);

            // Initializes the duck's speed
            int randomSpeed = (int) (Math.random() * 5) + 1;
            duck.setSpeed(randomSpeed);
        }

        window.setDucks(ducks);
        window.setScreenStatus(1);
    }

    public void updateDucks(){
        for(Duck d : ducks){
            // Updates the speed of each duck
            int randomSpeed = (int) (Math.random() * 5) - 2;
            d.setSpeed(d.getSpeed() + randomSpeed);

            // Keeps the duck speed within reasonable bounds
            if((d.getX() < 0 && d.getSpeed() < 0) || d.getSpeed() > MAX_SPEED || d.getSpeed() < -5){
                d.setSpeed(5);
            }

            // Updates the x value of each duck
            d.setX(d.getX() + d.getSpeed());
            if(d.getX() >= WINDOW_WIDTH){
                d.setX(0);
                d.setLaps(d.getLaps() + 1);
            }
        }
    }

    public Duck getLeader(){
        int topDuck = 0;
        int topLaps = Integer.MIN_VALUE;

        // Iterates through each duck and finds the duck that is in the lead
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

        // Returns the winning duck
        return ducks[topDuck];
    }

    public void timeOver(){
        // Stops the timer
        clock.stop();
        window.setScreenStatus(3);
    }

    public static void main(String[] args) {
        // Runs the game
        DuckTimer d = new DuckTimer();
        d.run();
    }

    public void run(){
        window.repaint();
    }
}
