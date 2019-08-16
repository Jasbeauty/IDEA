# Socket Communication
### [简单实现](#a)
### [IO 理解](#b)
### [NIO 理解](#c)
### [多线程实现服务端与多个客户端通信](#d)
### [两个客户端通信](#e)

# <span id="a">简单实现</span>
### 通信模型
* 建立连接
* 开始通信
* 结束通信

### Server
1、服务器建立通信`ServerSocket`

2、服务器建立`Socket`接收客户端连接

3、建立IO输入流`InputStream`读取客户端发送的数据

4、建立IO输出流`OutputStream`向客户端发送数据消息

5、关闭Socket及相关资源

> 为了解决阻塞、进行多线程通信，创建 `SocketThread` 类

```
public class SocketThread extends Thread {
    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // 获取一个输入流，接收客户端传递的信息
            InputStream is = socket.getInputStream();
            // 提高效率，将字节流转换为字符流
            InputStreamReader isr = new InputStreamReader(is);
            // 加入缓冲区
            BufferedReader br = new BufferedReader(isr);

            String temp = null;
            String infos = "";
            while ((temp = br.readLine()) != null) {
                infos += temp;
                System.out.println("server has connected with client.");
                System.out.println("client said: " + infos + " , client's property: " + socket.getInetAddress().getHostAddress());
            }

            // 获取一个输出流，向客户端发送信息
            OutputStream os = socket.getOutputStream();
            // 将输出流包装成打印流
            PrintWriter pw = new PrintWriter(os);
            pw.print("hi, server has received your messages");
            pw.flush();
            // 关闭输出流
            socket.shutdownOutput();

            // 关闭相对应的资源
            pw.close();
            os.close();
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

> 虽然解决了阻塞的问题，但整个系统的性能将会消耗非常大，因为多线程条件下的线程切换和创建都十分消耗时间，这将导致系统处理效率非常低

Server.java
```
 try {
    // 服务器建立通信ServerSocket
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("server is running ... ");
    
    while (true){
            // 倾听并接收此套接字的连接，返回一个socket对象
            Socket s = ss.accept();
            SocketThread thread = new SocketThread(s);
            thread.start();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
```
### Client
1、 创建`Socket`通信，设置通信服务器的`IP`和`Port`

2、建立IO输出流`OutputStream`向服务器发送数据消息

3、建立IO输入流`InputStream`读取服务器发送来的数据消息

4、关闭Socket及相关资源
```
        try {
            Socket s = new Socket("127.0.0.1", 8888);
            InputStream is = s.getInputStream();        // 获取一个输入流，接收服务端的信息
            OutputStream os = s.getOutputStream();      // 获取一个输出流，向服务端发送信息

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            // 客户端输入
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            }
            bw.write(input);
            bw.flush();
            s.shutdownOutput();     // 关闭输出流

            BufferedReader br = new BufferedReader(new InputStreamReader(is));      // 包装成字符流（提高效率），加入缓冲区
            String msg = br.readLine();
            System.out.println("Server said:" + msg);

            // 关闭相对应的资源
            br.close();
            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
```

# <span id="b">IO 理解</span>
### 重要的五个类和一个接口
接口：Serializable

类：File、OutputStream、InputStream、Writer、Reader
### 使用指南
* 输入、输出
    * 输入流：InputStream、Reader
    * 输出流：OutputStream、Writer
    > InputStream 和 OutputStream这两个是相辅相成的，InputStream 读取源文件夹下的数据文件，OutputStream 读取目的文件夹下的目的文件（一般是一个空文件，用来存储目的文件，实际上是一个copy），InputStream将读取的流字节传入 OutPutStream.read() 方法中，在该方法内部传入目的文件内
* 数据对象是否是纯文本
    * 是纯文本，选择字符流：Reader、Writer 
    * 不是纯文本，选择字节流 InputStream、OutputStream
* 具体的设备
    * 文件：`读`：FileInputStream、 FileReader；`写`：FileOutputStream、FileWriter
    * 数组：`byte[]`：ByteArrayInputStream、ByteArrayOutputStream；`char[]`：CharArrayReader、CharArrayWriter
    * Socket流：键盘输入，用`System.in`（一个InputStream对象）读取，用`System.out`（是一个OutoutStream对象）打印
* 转换成流，提高效率
    * 将 Stream 转化为 Reader、Writer 
* 使用缓冲提高效率
    * 加上 Buffered：BufferedInputStream, BufferedOuputStream, BufferedReader, BufferedWriter
    > * 缓冲作为一个中间的调度，帮助提高数据读取效率以及存储效率，通过使用缓冲这个装饰类来装饰传统的输入输出流，将文件每一次只能读取一个字节或字符问题更改成了每一次读取指定大小的字节数据或者字符数组，这样减少每一次输入输出过程中的打开关闭过程，提高效率
    > * 使用缓冲区的读取和写入，是想减少从磁盘中的读取和写入效率，将针对磁盘的读写压力放到缓冲区中，提高读写速度

### 四种 IO 状态
阻塞、非阻塞、同步、异步
> * 阻塞与非阻塞：针对线程而言，关键在于线程是否可以做别的
> * 同步与异步：针对消息通知机制，是主动获取（同步）还是被动接收（异步）

### 五种 IO 模型
* 阻塞式I/O

针对用户请求，整个系统状态从开始准备->准备中->准备完成返回，此间客户端从发起请求之后，并不会去做其它的事，直到服务端返回处理结果

* 非阻塞I/O

用户间隔时间段进行请求，在时间段内用户可以做其它事情，直到服务端返回处理结果

* I/O多路复用

客户端可以同时监听多个服务端处理

* 信号驱动I/O

客户端非阻塞，服务端准备好数据通知客户端

* 异步I/O

客户端发送请求之后，服务端直接返回结果，不论服务端数据是否准备好数据，等到服务端准备好数据，会将数据复制到用户进程中

> * java中的I/O包含了同步阻塞I/O
> * java中NIO包含了同步非阻塞I/O
> * NIO中的Reactor模式实现了I/O复用
> * AIO实现了异步I/O



# <span id="c">NIO 理解<span>
### 概述
NIO（Non-blocking I/O，在Java领域，也称为New I/O），是一种同步非阻塞的I/O模型，也是I/O多路复用的基础，已经被越来越多地应用到大型应用服务器，成为解决高并发与大量连接、I/O处理问题的有效方式
### 核心对象
`Channel` 和 `Buffer` 是NIO中的核心对象

> 到任何目的地（或来自任何地方）的所有数据都必须通过 `Channel` 对象。一个 `Buffer` 实际上是一个容器对象，发送给一个通道的所有对象都必须首先放到缓冲区中；同样地，从通道中读取的任何数据都要读到缓冲区中

* 常用的 `Channel`
    * FileChannel：文件操作通道
    * DatagramChannel：UDP操作通道
    * SocketChannel：TCP操作
    * ServerSocketChannel：TCP操作，使用在服务端 
* 常用的 `Buffer`
    * ByteBuffer
    > 一个 `ByteBuffer` 可以在其底层字节数组上进行 `get/set` 操作，`ByteBuffer` 并不是NIO中唯一的缓冲区类型，事实上对于每一种Java类型都有一种缓冲区类型（ByteBuffer、CharBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer）
### 优势
* 将最耗时的 I/O 操作(即填充和提取缓冲区)转移回操作系统，因而可以极大地提高速度
* 原来的 I/O 以`流`的方式处理数据，而 NIO 以`块`的方式处理数据，按`块`处理数据比按`(流式的)字节`处理数据要快得多

> [Reference--NIO](https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html)

# <span id="d">多线程实现服务端与多个客户端通信</span>
### 基本步骤
1、服务端创建ServerSocket绑定端口号，循环调用accept()方法

2、客户端创建一个socket并请求和服务器端连接

3、服务器端接受客户端请求，创建socket与该客户建立连接

4、两个socket在一个单独的线程上通话

5、服务器端继续等待新的连接

### Server
```java
package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器建立通信ServerSocket
 * 服务器建立Socket接收客户端连接
 * 建立IO输入流读取客户端发送的数据
 * 建立IO输出流向客户端发送数据消息
 */
public class Server {
    public static void main(String[] args) {
        try {
            // 服务器建立通信ServerSocket
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("server is running ... ");

            // 记录连接的客户端数量
            int n=0;
            while (true){
                // 倾听并接收此套接字的连接，返回一个socket对象
                Socket s = ss.accept();
                SocketThread thread = new SocketThread(s);
                thread.start();
                n++;
                System.out.println("There has been " + n + " clients connected with the server");
                System.out.println("Current client ip: " + s.getInetAddress() + ", " + "current client port: " + s.getPort());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```
服务器线程处理类
```
package socket;

import java.io.*;
import java.net.Socket;

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
```

### Client
```
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
                System.out.println("I am the client, server said:" + msg);
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

```
### 演示
Server
```html
server is running ... 
There has been 1 clients connected with the server
Current client ip: /127.0.0.1, current client port: 58791
Server has connected with client.
I am the server, client said: i am client one , client's property: 127.0.0.1
There has been 2 clients connected with the server
Current client ip: /127.0.0.1, current client port: 59020
Server has connected with client.
I am the server, client said: i am client two , client's property: 127.0.0.1
There has been 3 clients connected with the server
Current client ip: /127.0.0.1, current client port: 59027
Server has connected with client.
I am the server, client said: i am client xxx , client's property: 127.0.0.1

```
Client one
```html
键盘输入：
i am client one
接收到：
I am the client, server said:Hi client, server has received your messages: i am client one
```
Client two
```html
键盘输入：
i am client two
接收到：
I am the client, server said:Hi client, server has received your messages: i am client two
```
Client xxx
```html
键盘输入：
i am client xxx
接收到：
I am the client, server said:Hi client, server has received your messages: i am client xxx
```

# <span id="e">两个客户端通信</span>
### Basis
* #### Thread 中 run() 和 start() 的区别

##### start()
用start()来启动线程，真正实现了多线程运行，这时无需等待run()执行完毕而直接继续执行下面的代码

通过调用Thread类的start()方法来启动一个线程，这时此线程处于就绪（可运行）状态，但并没有运行，一旦得到cpu时间片，就开始执行run()方法，这里方法 run()称为线程体，它包含了要执行的这个线程的内容，Run()运行结束，此线程随即终止
##### run()
run()方法只是类的一个普通方法而已，如果直接调用run()，**程序中依然只有主线程这一个线程**，其程序执行路径还是只有一条，还是要顺序执行，还是要等待run()执行完毕后才可继续执行下面的代码

##### 总结
把需要并行处理的代码放在run()方法中，start()方法启动线程将自动调用 run()方法，这是由jvm的内存机制规定的

run()方法必须是public访问权限，返回值类型为void

* #### 守护线程（Daemon Thread）
Java的线程分为两种：User Thread（用户线程）、DaemonThread（守护线程）

守护线程，是指在程序运行的时候在后台提供一种通用服务的线程，其作用是为其他线程提供便利服务，比如垃圾回收线程就是一个很称职的守护者

User和Daemon两者几乎没有区别，唯一的不同之处就在于虚拟机的离开：如果 User Thread已经全部退出运行，只剩下Daemon Thread存在，虚拟机也就退出了（因为没有了被守护者，Daemon也就没有工作可做了，就没有继续运行程序的必要）

`thread.setDaemon(true)`必须在`thread.start()`之前设置，否则会跑出一个`IllegalThreadStateException`异常；你不能把正在运行的常规线程设置为守护线程

* #### throw、throws 和 try/catch/finally 的区别
##### throw
在程序中明确引发异常

throw出现在方法体中，用于抛出异常。当方法在执行过程中遇到异常情况时，将异常信息封装为异常对象，然后throw
##### throws
其作用是如果一个方法可以引发异常，而它本身并不对该异常处理，那么它必须将这个异常抛给调用它的方法

throws出现在方法的声明中，表示该方法可能会抛出的异常，允许throws后面跟多个异常类型
##### try/catch/finally
try的意思是试试它所包含的代码段中是否会发生异常；而catch当有异常时抓住它，并进行相应的处理，使程序不受异常的影响而继续执行下去；finally是无论发不发生异常都要被执行的代码

try出现在方法体中，它自身是一个代码块，表示尝试执行代码块的语句；如果在执行过程中有某条语句抛出异常，那么代码块后面的语句将不被执行

catch出现在try代码块的后面，自身也是一个代码块，用于捕获异常try代码块中可能抛出的异常；catch关键字后面紧接着它能捕获的异常类型，所有异常类型的子类异常也能被捕获

### Steps
> 具体代码可以参考 wenjiasun --> IDEA --> LeetCode --> Easy --> src --> socket 文件下的 `Server.java` 、`MsgHandler.java` 、`MsgClient.java` 、`Message.java`

##### 1、创建一个message类，里面存放用户自己的id `id`，要发送的对方id `targetID`，以及消息内容 `message`

```java
public class Message implements Serializable {
    int id;
    String message;
    int targetId;
}
```

##### `implements Serializable` Introduction
用来表示一个类的对象可以序列化
使用场景：
* 想把内存中的对象写入硬盘
> 比如当内存不够用，计算机就要将内存里面的一部分对象暂时的保存到硬盘中，等要用的时候再读入到内存中，硬盘的那部分存储空间就是所谓的虚拟内存
* 想用socket在网络上传输对象
> 为的是将数据变为二进制来传输，这样可以在网络上传输
* 想通过RMI传输对象
> 如果要通过 RMI             （远程的方法调用）去调用一个远程对象的方法，比如在计算机A中调用另一台计算机B的对象的方法，那么需要通过JNDI服务获取计算机B目标对象的引用，将对象从B传送到A，就需要实现序列化接口


##### 2、服务端使用Map来存放已连接的socket会话，每次有新的客户端连接，就建立一个新的线程，调用消息处理类 `MsgHandler.java` 实现多线程

```java
static Map<Integer, Socket> sessionMap = new HashMap<>();

private void newThread(int id, Socket s) {
    if (s != null) {
        // 将socket放入map, key为自己的客户端编号id
        sessionMap.put(id, s);
        // 开启处理本次会话
        Thread thread = new Thread(new MsgHandler(s, sessionMap, id));
      
        thread.start();
    }
}
```

##### 3、MsgHandler类用来处理接收客户端发送的信息，并转发给目标客户端
> 此处实现的是1和2，3和4...之间的通信，所以需要考虑需要发送的 `targetID`以及目标客户端还未连接的情况

* 客户端建立连接
```java
 s = new Socket("127.0.0.1", 8888);
 System.out.println("client has started ...");
```
* 服务端给新连接的客户端分配id，并发送欢迎消息
```java
public MsgHandler(Socket s, Map<Integer, Socket> sessionMap, int id) {
    this.s = s;
    this.sessionMap = sessionMap;
    try {
        Message message = new Message();
        // 客户端第一次连接后，接收到服务端发来的消息，此时id为客户端自己的id
        message.setMessage("Hello，client " + id);
        message.setId(id);
        // 客户端刚连接，所以给targetID初始化值为id
        message.setTargetId(id);
        send(message);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```
* 客户端接收到客户端发送的欢迎消息
```java
Socket s = null;
InputStream is = null;
InputStreamReader isr = null;
OutputStream os = null;

new Thread(() -> {
            while (true) {
                try {
                    is = s.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);
                    Message msg = (Message) ois.readObject();
                    System.out.println("I received: ------ " + msg.getMessage() + " ------");
                    ...
                    ...
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            // start()方法用来启动线程，实现了多线程运行
        }).start();
```
* 客户端发送消息给targetID（targetID根据奇偶判断）
```java
// 对方的id
private int targetId = 1;
// 自己的id
private int id = 1;

 // 获取当前用户自己的id
id = msg.getId();
// 根据奇偶判要发送的对方id
targetId = msg.getId() % 2 == 1 ? msg.getId() + 1 : msg.getId() - 1;

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
```
* 服务端转发消息
```java
public void run(){
    is = s.getInputStream();
    ObjectInputStream ois = new ObjectInputStream(is);
    // 实现一次连接，多次通话
    while (true) {
         // server端 读取数据
        Message msg = (Message) ois.readObject();
        System.out.println("Client id: " + msg.getId() + " , he said: " + msg.getMessage() + " , targetID: " + msg.getTargetId());
        
         // 发送数据
        try {
            send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ...
        ...
    }
}

private void output(Socket targetSocket,Message message) throws IOException {
    OutputStream os = targetSocket.getOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(os);
    oos.writeObject(message);
}

private void send(Message message) throws IOException {
    Socket targetSocket;
    if (sessionMap.get(message.getTargetId()) != null) {

        targetSocket = sessionMap.get(message.getTargetId());
        System.out.println("id: " + message.getId());
        System.out.println("targetId: " + message.getTargetId());


        output(targetSocket,message);
        System.out.println("Server has forward the message");
    }
    // 表示要发送的targetID对方客户端还未连接
    else {
        targetSocket = sessionMap.get(message.getId());
        message.setMessage("Sorry, the targetID is not exists ... ");

        output(targetSocket,message);
        System.out.println("Server failed to forward the message");
    }
}
```

### 演示
> 包含目标客户端还未连接上的情况，比如 client 1发给 client 2，但是 client 2 还未连接上
server
```html
server is running ... 
client 1 has connected with the server
id: 1
targetId: 1
Server has forward the message
Client id: 1 , he said: ddd , targetID: 2
Server failed to forward the message
client 2 has connected with the server
id: 2
targetId: 2
Server has forward the message
Client id: 2 , he said: dsdsssd , targetID: 1
id: 2
targetId: 1
Server has forward the message
client 3 has connected with the server
id: 3
targetId: 3
Server has forward the message
Client id: 3 , he said: 嘿嘿 , targetID: 4
Server failed to forward the message
client 4 has connected with the server
id: 4
targetId: 4
Server has forward the message
Client id: 4 , he said: 的点点滴滴 , targetID: 3
id: 4
targetId: 3
Server has forward the message
```
client 1
```html
client has started ...
I received: ------ Hello，client 1 ------
ddd
（ You have sent the message to client 2 )
I received: ------ Sorry, the targetID is not exists ...  ------
I received: ------ dsdsssd ------
```
client 2
```html
client has started ...
I received: ------ Hello，client 2 ------
dsdsssd
（ You have sent the message to client 1 )
```
client 3
```html
client has started ...
I received: ------ Hello，client 3 ------
嘿嘿
（ You have sent the message to client 4 )
I received: ------ Sorry, the targetID is not exists ...  ------
I received: ------ 的点点滴滴 ------
```
client 4
```html 
client has started ...
I received: ------ Hello，client 4 ------
的点点滴滴
（ You have sent the message to client 3 )
```
