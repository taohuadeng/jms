package com.util.excel;

import java.io.*;

/**
 * 序列化类
 */
public class SerializableUtil {

    public static void toSerializable(Object o, String filePath) throws IOException {
        //序列化一个对象(存储到一个文件)
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(o);
        oos.flush();
        oos.close();
    }

    public static byte[] toSerializableByte(Object o) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos2 = new ObjectOutputStream(outputStream);
        oos2.writeObject(o);
        oos2.flush();
        oos2.close();
        return outputStream.toByteArray();
    }

    public static <T> T toObject(String filePath, Class<T> entityClass) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        T t;
        t = (T) ois.readObject();
        ois.close();
        return t;
    }

    public static <T> T toObject(byte[] bytes, Class<T> entityClass) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        T t;
        t = (T) ois.readObject();
        ois.close();
        return t;
    }
}
