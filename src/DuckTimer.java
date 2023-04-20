public class DuckTimer {
    private DuckTimerViewer window;

    public DuckTimer(){
        window = new DuckTimerViewer(this);
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
