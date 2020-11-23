import classes.Tape;
import classes.Tolva;

public class Main {
    private static final int MAX_TOLVAS = 5;

    public static void main(String[] args) throws InterruptedException {
        // Crea Cinta
        Tape tape = new Tape();
        Thread tapeThread = new Thread(tape, "Cinta");

        // Crea las tolvas
        for (int i = 0; i < MAX_TOLVAS; i++) {
            new Thread(new Tolva(tape), "Tolva # " + i).start();
        }

        // Duerme 3 segundo
        Thread.sleep(3000);
        // Empiza la cinta
        tapeThread.start();
    }
}
