import java.util.Scanner;

public class Test {



    public static void main(String[] args) throws Exception {
//        Scanner input = new Scanner(System.in);
//        int i = input.nextInt();
//        if (i > 10) {
//            throw new Exception("wrong");
//        }

        Chinese obj1 = Chinese.getInstance();
        Chinese obj2 = Chinese.getInstance();
        System.out.println(obj1 == obj2);
    }

    public final static native int w();

    public int aMethod(){
        int i = 0;
        i++;
        return i;
    }



}
class Chinese{
    private static Chinese objref =new Chinese();
    private Chinese(){}
    public static Chinese getInstance() { return objref; }
}