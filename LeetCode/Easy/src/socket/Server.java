package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务器建立通信ServerSocket
 * 服务器建立Socket接收客户端连接
 * 建立IO输入流读取客户端发送的数据
 * 建立IO输出流向客户端发送数据消息
 */
public class Server {
    // 用来存放已连接客户端的socket会话
    static Map<Integer, Socket> sessionMap = new HashMap<>();

    // 默认客户端初始化id为1
    private int id = 1;

    public void socket() {
        try {
            // 服务器建立通信ServerSocket
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("server is running ... ");

//
//            // 服务器建立Socket接收客户端连接
//            Socket s = ss.accept();
//            System.out.println("client: " + s.getInetAddress().getHostAddress() + " has connected to the server.");
//            // 建立IO输入流 读取客户端发送的数据
//            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//            String msg = br.readLine();
//            System.out.println("client said: " + msg);
//            // 建立IO输出流 向客户端发送数据消息
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
//            bw.write("hi client, server has received the message.");
//            bw.flush();
//            s.shutdownOutput();     // 关闭输出流
//
//            // 关闭相对应的资源
//            br.close();
//            s.close();

//            int n = 0;
            while (true) {
                Socket s = ss.accept();
                System.out.println("client " + id + " has connected with the server");
                newThread(id, s);
                id ++;


//                if (id == 1) {
//                    // 倾听并接收此套接字的连接，返回一个socket对象
//                    Socket s = ss.accept();
//                    System.out.println("client " + id + " has connected with the server");
//                    newThread(id, s);
//                }else {
//                    id = 2;
//                    // 倾听并接收此套接字的连接，返回一个socket对象
//                    Socket s = ss.accept();
//                    System.out.println("client " + id + " has connected with the server");
//                    newThread(id, s);
//                }


//                n++;
//                System.out.println("There has been " + n + " clients connected with the server");
//                System.out.println("Current client ip: " + s.getInetAddress() + ", " + "current client port: " + s.getPort());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newThread(int id, Socket s) {
        if (s != null) {
            // 将socket放入map, key为自己的客户端编号id
            sessionMap.put(id, s);
            // 开启处理本次会话
            Thread thread = new Thread(new MsgHandler(s, sessionMap, id));
            /**
             *   1、守护线程 (Daemon Thread)
             2、所谓守护线程，是指在程序运行的时候在后台提供一种通用服务的线程，作用是为其他线程提供便利服务，比如垃圾回收线程就是一个很称职的守护者
             3、thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程
             */
//            thread.setDaemon(true);


            // thread.run()方法是一个普通的对象方法，不是线程调用start()后才可以调用，可以随时被调用
            thread.start();

//                    SocketThread thread = new SocketThread(s);
//                    thread.start();
        }
    }

    public static void main(String[] args) {
        new Server().socket();
    }
}
