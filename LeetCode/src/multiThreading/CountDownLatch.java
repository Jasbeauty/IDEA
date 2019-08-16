package multiThreading;

import java.util.concurrent.CountDownLatch;

// A class to represent threads for which the main thread waits.
class Worker extends Thread {
    private int delay;
    private CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch,
                  String name) {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            // The thread, which is dependent on other threads to start processing, waits on until every other thread has called countDown().
            latch.countDown();
            System.out.println(Thread.currentThread().getName()
                    + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

