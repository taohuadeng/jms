package com.share.serializable;

import com.tbc.soa.json.JSONSerializer;
import com.tbc.soa.json.JsonCodec;
import com.util.excel.SerializableUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static class Mouse implements Serializable{
        private Map<String, Object> mouseMap;

        public Map<String, Object> getMouseMap() {
            return mouseMap;
        }

        public void setMouseMap(Map<String, Object> mouseMap) {
            this.mouseMap = mouseMap;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p = SerializableUtil.toObject("test.out", Person.class);
        System.out.println(p);


        Duck duck = new Duck("唐老鸭", 10);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("米老鼠", 9);
        duck.setMap(map);
//        byte[] bytes = SerializableUtil.toSerializableByte(duck);
//        String javaDuck = new String(bytes, "ISO8859-1");
//        System.out.println("Java serializable length = " + javaDuck.length());

        Mouse mouse = new Mouse();
        Map<String, Object> mouseMap = new HashMap<String, Object>();
        mouseMap.put("米老鼠", 9);
        mouse.setMouseMap(mouseMap);

        byte[] bytes = SerializableUtil.toSerializableByte(mouse);
        String javaMouse = new String(bytes, "ISO8859-1");
        System.out.println("Java serializable length = " + javaMouse.length());
        Mouse newMouse = SerializableUtil.toObject(bytes, Mouse.class);
        System.out.println(newMouse);

        JSONSerializer serialize = new JSONSerializer();
        String jsonSerializableMouse = serialize.deepSerialize(mouse);
        System.out.println(jsonSerializableMouse);
//        String jsonSerializableDuck = serialize.deepSerialize(duck);
//        System.out.println(jsonSerializableDuck);
        Dog dog = new Dog();
        dog.setDogName("旺旺");
        dog.setDogAge(20);
        String jsonSerializableDog = serialize.deepSerialize(dog);
        System.out.println(jsonSerializableDog);

        JsonCodec jsonCodec = new JsonCodec();
        String json = jsonCodec.toJson(mouse);
        System.out.println(json.length());

        Mouse mouseJson = (Mouse) jsonCodec.toObject(json, Mouse.class);
        System.out.println(mouseJson);
    }
}

class Duck implements Serializable {
    private String duckName;
    private int duckAge;
    private Map<String, Object> map;

    Duck() {
    }

    Duck(String duckName, int duckAge) {
        this.duckAge = duckAge;
        this.duckName = duckName;
    }

    @Override
    public String toString() {
        return "Duck{" +
                "duckName='" + duckName + '\'' +
                ", duckAge=" + duckAge +
                '}';
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}




