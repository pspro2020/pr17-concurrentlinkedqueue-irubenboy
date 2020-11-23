package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Tolva implements Runnable{

    private final ConcurrentLinkedDeque<Plastic> listElements = new ConcurrentLinkedDeque<>();
    private static final int MAX_ELEMENTS = 5;
    private final Tape tape;
    private final DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");


    public Tolva(Tape tape){
        for (int i = 0; i < MAX_ELEMENTS; i++) {
            listElements.add(new Plastic(i+1));
        }
        this.tape = tape;
    }

    @Override
    public void run() {
        try {
            dropPlastic();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s has been interrupted while dropped plastic\n", LocalDateTime.now().format(f),
                    Thread.currentThread().getName());
            return;
        }

        System.out.printf("%s -> %s doesn't have more plastic to drop\n", LocalDateTime.now().format(f),
                Thread.currentThread().getName());
    }

    private void dropPlastic() throws InterruptedException {
        Plastic p = listElements.remove();
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2)+1);
        tape.add(p);
        System.out.printf("%s -> %s has dropped the %s\n", LocalDateTime.now().format(f),
                Thread.currentThread().getName(), p.getName());
    }
}
