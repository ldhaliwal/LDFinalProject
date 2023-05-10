import java.awt.*;

public class Duck {
    private int speed;

    private int laps;
    private Image duckImage;
    private final int number;
    private int x = 50;
    private int y;
    private DuckTimerViewer window;

    public Duck (int number, DuckTimerViewer window){
        this.number = number;
        this.window = window;
        laps = 0;

        y = 800 - ((400/window.getD().getNumDucks()) * number);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getDuckImage() {
        return duckImage;
    }

    public void setDuckImage(Image duckImage) {
        this.duckImage = duckImage;
    }

    public int getNumber() {
        return number;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }
}
