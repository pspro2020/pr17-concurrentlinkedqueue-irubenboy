package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Tape implements Runnable {
    private final ConcurrentLinkedDeque<Plastic> listElements = new ConcurrentLinkedDeque<>();
    private DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void add(Plastic p) {
        listElements.add(p);
    }

    @Override
    public void run() {
        System.out.printf("%s -> %s has been started", LocalDateTime.now().format(f),
                Thread.currentThread().getName());

        try {
            processPlastic();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while processing", LocalDateTime.now().format(f),
                    Thread.currentThread().getName());
        }
    }

    private void processPlastic() throws InterruptedException {
        Plastic p = listElements.remove();
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2)+1);
        System.out.printf("%s -> %s has processed the %s", LocalDateTime.now().format(f),
                Thread.currentThread().getName(), p.getName());
    }
}
