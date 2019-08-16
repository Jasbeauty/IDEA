package multiThreading;

//JAVA program to demonstrate execution on Cyclic Barrier

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Computation1 implements Runnable {
    public static int product = 0;

    public void run() {
        product = 2 * 3;
        try {
            // await(): Waits until all parties have invoked await on this barrier, or the specified waiting time elapses.
            Tester.newBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class Computation2 implements Runnable {
    public static int sum = 0;

    public void run() {
        // check if newBarrier is broken or not
        // isBroken(): true if one or more parties broke out of this barrier due to interruption or timeout since construction or the last reset, or a barrier action failed due to an exception; false otherwise.
        System.out.println("Is the barrier broken? - " + Tester.newBarrier.isBroken());
        sum = 10 + 20;
        try {
            Tester.newBarrier.await(3000, TimeUnit.MILLISECONDS);

            // the number of parties currently blocked in await()
            // getNumberWaiting(): Returns the number of parties currently waiting at the barrier.
            System.out.println("Number of parties waiting at the barrier " +
                    "at this point = " + Tester.newBarrier.getNumberWaiting());
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}


public class Tester implements Runnable {
    public static CyclicBarrier newBarrier = new CyclicBarrier(3);



    public static void main(String[] args) {
        // parent thread
        Tester test = new Tester();

        Thread t1 = new Thread(test);
        t1.start();
    }

    public void run() {
        // getParties(): Returns the number of parties required to trip this barrier
        System.out.println("Number of parties required to trip the barrier = " +
                newBarrier.getParties());
        System.out.println("Sum of product and sum = " + (Computation1.product +
                Computation2.sum));

        // objects on which the child thread has to run
        Computation1 comp1 = new Computation1();
        Computation2 comp2 = new Computation2();

        // creation of child thread
        Thread t1 = new Thread(comp1);
        Thread t2 = new Thread(comp2);

        // moving child thread to runnable state
        t1.start();
        t2.start();

        try {
            // await() method returns the arrival index of the current thread, where index getParties() – 1 indicates the first to arrive and zero indicates the last to arrive
            Tester.newBarrier.await();
            // BrokenBarrierException：A barrier breaks when any of the waiting thread leaves the barrier. This happens when one or more waiting thread is interrupted or when the waiting time is completed because the thread called the await() methods with a timeout
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        // barrier breaks as the number of thread waiting for the barrier at this point = 3
        System.out.println("Sum of product and sum = " + (Computation1.product +
                Computation2.sum));

        // Resetting the newBarrier
        // reset(): Resets the barrier to its initial state. If any parties are currently waiting at the barrier, they will return with a BrokenBarrierException.
        newBarrier.reset();
        System.out.println("Barrier reset successful");
    }
}

