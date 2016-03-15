package com.share.innerclass;

import com.share.clone.CloneDemo;

public class Test {
    public static void main(String[] args) {
        Circle.Draw draw = new Circle(2).new Draw();
        draw.drawShape();
    }

}

class Circle {
    private double radius = 0;
    public static int count =1;
    public Circle(double radius) {
        this.radius = radius;
    }

    class Draw {     //内部类
        public void drawShape() {
            System.out.println(radius);  //外部类的private成员
            System.out.println(count);   //外部类的静态成员
            System.out.println(Circle.this.radius);   //外部类的静态成员
        }
    }
}
