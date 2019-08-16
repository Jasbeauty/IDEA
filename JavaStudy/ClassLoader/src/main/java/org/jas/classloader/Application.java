package org.jas.classloader;

import org.jas.classloader.exec.VersionControl;
import org.jas.classloader.loader.FileSystemClassLoader;

import java.util.Timer;
import java.util.TimerTask;

public class Application {
    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String swapPath = "/Users/wenjiasun/IDEA/JavaStudy/ClassLoader/target/classes/";
                String className = "org.jas.classloader.exec.VersionControl";

                FileSystemClassLoader classLoader = new FileSystemClassLoader(swapPath);


                try {
                    VersionControl versionControl = (VersionControl) classLoader.loadClass(className).newInstance();
                    versionControl.printVersion();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
    }
}
