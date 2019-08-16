package easy.demos;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/wenjiasun/IDEA/LeetCode/src/easy/demos/newFile.txt");
        if (!file.exists()) {
//            System.out.println(file.mkdirs());    // 试图创建一个File对象所对应的目录，如果创建成功，则返回true（（调用该方法时File对象必须对应一个路径，而不是一个文件））
            System.out.println(file.createNewFile());   // 当此File对象所对应的文件不存在时，该方法将新建的一个该File对象所指定的新文件
        }
        file.setReadable(true);
        file.setWritable(false);
        System.out.println("isFile:" + file.isFile() + "\tisDirectory:" + file.isDirectory() + "\tcanRead:" + file.canRead() + "\tcanWrite:" + file.canWrite());


        System.out.println("lasrModified(): " + new Date(file.lastModified()));
        System.out.println("length(): " + file.length() + " ,absolutePath(): " + file.getAbsolutePath());
        System.out.println("getPath():" + file.getPath() + " ,getName(): " + file.getName());

//        String[] str = file.list();     //  列出File对象的所有子文件名和路径名，返回String数组
//        for (int i = 0; i < str.length; i++) {
//            System.out.println(": " + str[i]);
//        }
    }
}
