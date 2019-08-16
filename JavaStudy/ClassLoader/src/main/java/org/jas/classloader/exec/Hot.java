package org.jas.classloader.exec;

public class Hot {
    public void hot() {
        System.out.println("version 2111: " + this.getClass().getClassLoader());
    }
}
