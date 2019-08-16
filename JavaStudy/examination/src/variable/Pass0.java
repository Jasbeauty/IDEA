package variable;

class Two{
    Byte x;
}
class PassO{
    final String S1 = "11";

    public static void main(String[] args){
        PassO p=new PassO();
        p.start();
    }
    void start(){
        Two t=new Two();
        System.out.println(t.x+"");

        Two t2=fix(t);
        System.out.println(t.x+"" +t2.x);

        System.out.println(t);
        System.out.println(t2);

        Two t3 = new Two();
        t3.x = 45;
        fix1(t3);
        System.out.println(t3.x);
    }
    Two fix(Two tt){
        tt.x=42;
        return tt;
    }

    void fix1(Two tt) {
//        tt = new Two();
        tt.x = 44;
    }
}
