package socket;

import java.io.*;
import java.net.Socket;
import java.util.Map;

/**
 * 消息处理类
 */
public class MsgHandler extends Thread {
    Socket s;
    InputStream is = null;
    Map<Integer, Socket> sessionMap;

    public MsgHandler(Socket s, Map<Integer, Socket> sessionMap, int id) {
        this.s = s;
        this.sessionMap = sessionMap;
        try {
            Message message = new Message();
            // 客户端第一次连接后，接收到服务端发来的消息，此时id为客户端自己的id
            message.setMessage("Hello，client " + id);
            message.setId(id);
            // 客户端刚连接，所以给targetID初始化的值为id
            message.setTargetId(id);
            send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            // 实现一次连接，多次通话
            while (true) {
                try {
                    // server端 读取数据
                    Message msg = (Message) ois.readObject();
                    System.out.println("Client id: " + msg.getId() + " , he said: " + msg.getMessage() + " , targetID: " + msg.getTargetId());
                    // 发送数据
                    try {
                        send(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (s.isClosed()) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        s.close();
                        break;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * message.id : target id  即目标id
     *
     * @param message
     * @throws IOException
     */
    private void send(Message message) throws IOException {
        Socket targetSocket;
        if (sessionMap.get(message.getTargetId()) != null) {
//            if (message.getTargetId() == 0) {
//                targetSocket = sessionMap.get(message.getId());
//
//            } else {
//
//            }
            targetSocket = sessionMap.get(message.getTargetId());
            System.out.println("id: " + message.getId());
            System.out.println("targetId: " + message.getTargetId());

//            OutputStream os = targetSocket.getOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(os);
//            oos.writeObject(message);
            output(targetSocket,message);
            System.out.println("Server has forward the message");
        }
        // 表示要发送的targetID对方客户端还未连接
        else {
            targetSocket = sessionMap.get(message.getId());
            message.setMessage("Sorry, the targetID is not exists ... ");
//            OutputStream os = targetSocket.getOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(os);
//            oos.writeObject(message);
            output(targetSocket,message);
            System.out.println("Server failed to forward the message");
        }
    }
    private void output(Socket targetSocket,Message message) throws IOException {
        OutputStream os = targetSocket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(message);
    }
}

