import javax.swing.*;
import java.awt.*;

public class DuckTimerViewer extends JFrame{
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;

    private Image image;
    private DuckTimer d;
    private int screenStatus;

    public DuckTimerViewer(DuckTimer d){
        this.d = d;

        screenStatus = 0;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Duck Timer");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void setScreenStatus(int screenStatus) {
        this.screenStatus = screenStatus;
    }

    public void paint (Graphics g){
        if(screenStatus == 0){
            //  image = new ImageIcon().getImage();
            // g.drawImage(image, 0, 22, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        }
    }

}
