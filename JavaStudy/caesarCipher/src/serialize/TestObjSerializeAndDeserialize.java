package serialize;

import java.io.*;
import java.text.MessageFormat;

public class TestObjSerializeAndDeserialize {
    public static void main(String[] args) throws Exception{
        SerializePerson();
        Person p = DeserializePerson();
//        System.out.println(p);
        System.out.println(MessageFormat.format("name={0}, age={1}, sex={2}",p.getName(),p.getAge(),p.getSex()));
    }
    /**
     * 序列化Person对象
     */
    private static void SerializePerson() throws IOException{
        Person person = new Person();
        person.setName("fortunate");
        person.setSex("female");
        person.setAge(26);
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("Person.txt")));
        oo.writeObject(person);
        System.out.println("Person对象 序列化成功！");
        oo.close();
    }
    /**
     * 反序列化Person对象
     */
    private static Person DeserializePerson() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("Person对象 反序列化成功！");
        return person;
    }
}
