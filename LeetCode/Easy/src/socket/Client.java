package socket;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 创建Socket通信，设置通信服务器的IP和Port
 * 建立IO输出流向服务器发送数据消息
 * 建立IO输入流读取服务器发送来的数据消息
 */

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 8888);

            InputStream is = s.getInputStream();        // 获取一个输入流，接收服务端的信息
            OutputStream os = s.getOutputStream();      // 获取一个输出流，向服务端发送信息
            String input = "";

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            // 客户端键盘输入
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            }
            bw.write(input);
            bw.flush();
            s.shutdownOutput();     //必须加上这句话，表示输出流的结束，注意此时不能关闭os,因为关闭os会关闭绑定的socket

            // 获取回应消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));      // 包装成字符流（提高效率），加入缓冲区
            String msg = null;
            while ((msg = br.readLine()) != null) {
                System.out.println(" am the client, server said:" + msg);
            }

            // 关闭相对应的资源
            br.close();
            is.close();
            bw.close();
            os.close();
            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
