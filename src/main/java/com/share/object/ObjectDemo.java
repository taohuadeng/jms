package com.share.object;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ObjectDemo {

    private final List synchronizedList;

    public ObjectDemo() {
        // create a new synchronized list to be used
        synchronizedList = Collections.synchronizedList(new LinkedList());
    }

    // method used to remove an element from the list
    public String removeElement() throws InterruptedException {
        synchronized (synchronizedList) {
            // while the list is empty, wait
            while (synchronizedList.isEmpty()) {
                System.out.println("List is empty...");
                synchronizedList.wait();
                System.out.println("Waiting...");
            }

            String remove = (String) synchronizedList.remove(0);
            System.out.println("remove " + remove);
            return remove;
        }
    }

    // method to add an element in the list
    public void addElement(String element) {
        System.out.println("Opening...");
        synchronized (synchronizedList) {
            // add an element and notify all that an element exists
            synchronizedList.add(element);
            System.out.println("New Element:'" + element + "'");

            synchronizedList.notifyAll();
            System.out.println("notifyAll called!");
        }
        System.out.println("Closing...");
    }

    public static void main(String[] args) {
        final ObjectDemo demo = new ObjectDemo();
        Runnable runA = new Runnable() {
            public void run() {
                try {
                    String item = demo.removeElement();
                    System.out.println("" + item);
                } catch (InterruptedException ix) {
                    System.out.println("Interrupted Exception!");
                } catch (Exception x) {
                    System.out.println("Exception thrown.");
                }
            }
        };

        Runnable runB = new Runnable() {
            // run adds an element in the list and starts the loop
            @Override
            public void run() {
                demo.addElement("Hello!");
            }
        };

        try {
            Thread threadA1 = new Thread(runA, "A");
            threadA1.start();
            Thread.sleep(500);

            Thread threadA2 = new Thread(runA, "B");
            threadA2.start();
            Thread.sleep(500);

            Thread threadB = new Thread(runB, "C");
            threadB.start();
            Thread.sleep(1000);

            threadA1.interrupt();
            threadA2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}