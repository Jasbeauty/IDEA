package socket;

import java.io.*;
import java.net.Socket;

/**
 * 消息接收--客户端
 */
public class MsgClient {
    Socket s = null;
    InputStream is = null;
    InputStreamReader isr = null;
    OutputStream os = null;
    // 对方的id
    private int targetId = 1;
    // 自己的id
    private int id = 1;



    public void socketStart() {
        try {
            s = new Socket("127.0.0.1", 8888);
            System.out.println("client has started ...");
            // 接收数据
            new Thread(() -> {
                while (true) {
                    try {
                        is = s.getInputStream();
                        ObjectInputStream ois = new ObjectInputStream(is);
                        Message msg = (Message) ois.readObject();
                        System.out.println("I received: ------ " + msg.getMessage() + " ------");
                        // 获取当前用户自己的id
                        id = msg.getId();
                        // 根据奇偶判要发送的对方id
                        targetId = msg.getId() % 2 == 1 ? msg.getId() + 1 : msg.getId() - 1;
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                // start()方法用来启动线程，实现了多线程运行
            }).start();

            // 发送数据
            os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            while (true) {
                // 键盘输入
                isr = new InputStreamReader(System.in);
                String msg = new BufferedReader(isr).readLine();
                Message text = new Message();
                // 此时的id值是对方id
                text.setTargetId(targetId);
                text.setId(id);
                text.setMessage(msg);
                oos.writeObject(text);
                System.out.println("（ You have sent the message to client " + targetId + " )");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MsgClient().socketStart();
    }
}
