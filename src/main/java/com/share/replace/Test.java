package com.share.replace;

public class Test {
    public static void main(String[] args) {
        String s = "|abcd|abcd";
        String replace = s.replace("|", "b");
        System.out.println(replace);
        String replaceAll = s.replaceAll("\\|", "x");
        System.out.println(replaceAll);
        Class<? extends String> aClass = s.getClass();
        System.out.println(aClass);

        CloneDemo demo = new CloneDemo();

    }
}

class CloneDemo {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
