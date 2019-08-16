package easy.demos;

public class HelloSogou{
    public static synchronized void main(String[] a){
        Thread t=new Thread(){
            public void run(){Sogou();}
        };
        t.run();
        System.out.print("Hello");


        Boolean flag = false;
        if (flag = true)
        {
            System.out.println("true");
        }
        else
        {
            System.out.println("false");
        }

        HelloSogou helloSogou = new HelloSogou();
        helloSogou.test();
    }

    public void add(Byte b)
    {
        b = b++;
    }
    public void test()
    {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.print(a + " ");
        add(b);
        System.out.print(b + "");
    }

    static synchronized void Sogou(){
        System.out.print("Sogou");
    }
}
