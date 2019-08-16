package demos;

public class Bground extends Thread{
    public static void main(String argv[]){
        Bground b = new Bground();
//        b.start();
        String s = "hello";
        String t = "hello";
        char c [ ] = {'h','e','l','l','o'};
        System.out.println(s.equals(t));
        System.out.println(t.equals(c));
        System.out.println(s==t);
        System.out.println(t.equals(new String("hello")));

        java.util.HashMap map=new java.util.HashMap();
        map.put("name",null);
        map.put("name","Jack");
        System.out.println(map.size());

        String s1 = "ABCD";

        String s2 = "1234";

        System.out.println(s1 + s2);

        Thread[] ths = new Thread[3];
        for (int i = 0; i < 3; i++) {
            Runnable target;
            ths[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(j);
                    }
                    System.out.println(" ");
                }
            });
        }
        for (Thread th :
                ths) {
            th.start();
        }

        Object sq = 1.5f;


    }
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("Value of i = "+i);
        }
    }
}
