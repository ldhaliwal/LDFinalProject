import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DuckTimer implements ActionListener {
    private static final int SLEEP_TIME = 110;
    private DuckTimerViewer window;
    private Timer clock;

    //private [] duckImages;
    private Duck[] ducks;

    private int numDucks;

    public DuckTimer(){
        window = new DuckTimerViewer(this);

        for(int i = 0; i < 21; i++){

        }
    }

    public void actionPerformed(ActionEvent e) {
        window.repaint();
    }

    public void startClock(){
        clock = new Timer(SLEEP_TIME, this);
        clock.start();
    }

    public void setNumDucks(int num){
        numDucks = num;
    }

    public void createDucks(){
        ducks = new Duck[numDucks];
        for (int i = 0; i < numDucks; i++){
            Duck duck = new Duck(i, window);
            ducks[i] = duck;
            //duck.setDuckImage(duckImages[i]);
        }

        int winDuck = (int) (Math.random() * numDucks);
        ducks[winDuck].setWinningDuck(true);
    }

    public static void main(String[] args) {
        //runs the game
        DuckTimer d = new DuckTimer();
        d.run();
    }

    public void run(){
        window.setScreenStatus(0);
        window.repaint();
    }
}
