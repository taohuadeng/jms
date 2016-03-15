package com.share.serializable;

import com.util.excel.SerializableUtil;

import java.io.*;

public class Person implements Serializable {
    private String userName;
    private String password;
    private static int age = 123;

    public Person(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String toString() {
        return "userName:" + userName + "  password:" + password + "  age:" + age;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
//        Person person = new Person("陶发登", "111111");
//        SerializableUtil.toSerializable(person, "test.out");
//        Person.age = 15;
//        person.password = "222222";
        Person p = SerializableUtil.toObject("test.out", Person.class);
        System.out.println(p);

//        byte[] bytes = SerializableUtil.toSerializableByte(person);
//        Person p2 = SerializableUtil.toObject(bytes, Person.class);
//        String s = new String(bytes, "ISO8859-1");
//        System.out.println(p2);
//        System.out.println("java序列化长度 = " + s.length());
//        byte[] newByte = s.getBytes("ISO8859-1");
//        Person p3 = SerializableUtil.toObject(newByte, Person.class);
//        System.out.println(p3);

//        //序列化一个对象(存储到一个文件)
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.out"));
//        oos.writeObject("Save a object:\n");
//        oos.writeObject(new Person("Bruce", "123456"));
//        oos.writeObject(new Person("TaoHuaDeng", "000000"));
//        oos.close();
//
//        //反序列化,将该对象恢复(存储到一个文件)
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.out"));
//        String s = (String) ois.readObject();
//        Person p = (Person) ois.readObject();
//        Person p2 = (Person) ois.readObject();
//        //Person p3 = (Person) ois.readObject();
//        System.out.println(s + p);
//        System.out.println(p2);
//        //System.out.println(p3);
//
        //序列化一个对象(存储到字节数组)
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos2 = new ObjectOutputStream(baos);
//        oos2.writeObject("Save another object:\n");
//        oos2.writeObject(new Person("Phil", "654321"));
//        oos2.close();
//        byte[] bytes = baos.toByteArray();
//        baos = new ByteArrayOutputStream(bytes);
//
//        //反序列化,将该对象恢复(存储到字节数组)
//        ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
//        s = (String) ois2.readObject();
//        p = (Person) ois2.readObject();
//
//        System.out.println(s + p);
    }
}