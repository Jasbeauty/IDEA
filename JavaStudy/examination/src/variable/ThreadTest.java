package variable;

import java.util.concurrent.TimeUnit;

public class ThreadTest {
    static class Thread1 implements Runnable {

        private Message message;

        private int b;

        public Thread1(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            change(message);
        }

        private void change(Message message) {
            int a = 0;
            b ++;
            System.out.println(message.getMess() + " " + b);
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Message message1 = new Message("1");
        Message message2 = new Message("2");

        Thread1 t1 = new Thread1(message1);
        Thread1 t2 = new Thread1(message2);

        new Thread(t1).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(t1).start();

    }
}

class Message {
    private String mess;

    public Message(String mess) {
        this.mess = mess;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}