package easy.demos;

public class Demo {
    static int cnt = 6;

    static {
        cnt += 9;
    }

    static int arr[] = new int[5];
    String str=new String("tarena");
    char[]ch={'a','b','c'};
    private static int x=100;
    public static void main(String[] args) {
//        System.out.println("cnt =" + cnt);
//        complicatedexpression_r();
//        stringCp();
//        System.out.println(arr[0]);
//        int a = Integer.parseInt("1024");
//
//        int b = Integer.valueOf("1024").intValue();
//        System.out.println(a==b);
//        double x=2.0; int y=4; x/=++y;
//        System.out.println(x);



        Demo ex=new Demo();
        ex.x++;
        Demo ex1 = new Demo();
        ex1.x++;
        ex = new Demo();
        ex.x++;
        Demo.x--;
        System.out.println(x);


    }


    public void change(String str,char ch[]){
        //引用类型变量，传递的是地址，属于引用传递。
        str="test ok";
        ch[0]='g';
    }

    static {
        cnt /= 3;
    }



    public static void complicatedexpression_r() {
        int x = 20, y = 30;
        boolean b;
        b = x > 50 && y > 60 || x > 50 && y < -60 || x < -50 && y > 60 || x < -50 && y < -60;
        System.out.println(b);
    }

    public static void stringCp() {
        String str = new String("hello");
        if (str == "hello") {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }


}
