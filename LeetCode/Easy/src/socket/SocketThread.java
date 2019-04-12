package socket;

import java.io.*;
import java.net.Socket;

/**
 * 服务器线程处理类
 * 实现多线程
 */
public class SocketThread extends Thread {
    private Socket socket = null;
    private InputStream is = null;
    private BufferedReader br = null;
    private OutputStream os = null;
    private PrintWriter pw = null;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }



    public void run() {
        try {
            // 获取一个输入流，接收客户端传递的信息
            is = socket.getInputStream();
            // 提高效率，将字节流转换为字符流
            InputStreamReader isr = new InputStreamReader(is);
            // 加入缓冲区
            br = new BufferedReader(isr);

            String temp = null;
            // 用于记录接收到的用户信息
            String infos = "";
            while ((temp = br.readLine()) != null) {
                infos += temp;
                System.out.println("Server has connected with client.");
                System.out.println("I am the server, client said: " + infos + " , client's property: " + socket.getInetAddress().getHostAddress());
            }
            socket.shutdownInput();     //必须要及时关闭，因为readline()这个方法是以\r\n作为界定符的，由于发送消息的那一端用的是PrintWriter的write()方法，这个方法并没加上\r\n,所以会一直等待

            // 获取一个输出流，向客户端发送信息
            os = socket.getOutputStream();
            // 将输出流包装成打印流
            pw = new PrintWriter(os);
            pw.print("Hi client, " + "server has received your messages: " + infos);
            pw.flush();     // flush()强制将缓冲区中的数据发送出去,不必等到缓冲区满
            // 关闭输出流
//            socket.shutdownOutput();

            // 关闭相对应的资源
//            pw.close();
//            os.close();
//            br.close();
//            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (pw!=null){
                pw.close();
            }
            try {
                if (os!=null){
                    os.close();
                }
                if (br!=null){
                    br.close();
                }
                if (is!=null){
                    is.close();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
