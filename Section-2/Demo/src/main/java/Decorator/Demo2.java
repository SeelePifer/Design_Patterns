package Decorator;

import java.awt.*;
import java.util.function.Supplier;

interface Shape{
    String info();
}
class Circle implements Shape{

    private float radius;

    public Circle() { }

    public Circle(float radius) {
        this.radius = radius;
    }
    void resize(float factor){
        radius*= factor;
    }

    @Override
    public String info() {
        return "A circle of radius: "+radius;
    }
}
class Square implements Shape{

    private float side;

    public Square() {
    }

    public Square(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A square side "+side;
    }
}
class ColorShape<T extends Shape> implements Shape{

    private Shape shape;
    private String color;

    public ColorShape(Supplier<? extends T> ctor,
                      String color)  {
        shape=ctor.get();
        this.color=color;


    }

    @Override
    public String info() {
        return shape.info()+ " has the color "+color;
    }
}
class TransparentShape<T extends Shape> implements Shape{

    private Shape shape;
    private int transparency;

    public TransparentShape(Supplier<? extends T> ctor,
                            int transparency) {
        this.shape = ctor.get();
        this.transparency=transparency;
    }

    @Override
    public String info() {
        return shape.info()+" has "+transparency+" % transparency";
    }
}
public class Demo2 {
    public static void main(String[] args) {
        ColorShape<Square> blueSquare = new ColorShape<>(
                ()->new Square(20),
                "blue"
        );
        System.out.println(blueSquare.info());
        TransparentShape<ColorShape<Circle>> mycircle =
                new TransparentShape<>(()->
                        new ColorShape<>(()-> new Circle(5),
                                "green"),50);
        System.out.println(mycircle.info());

    }
}
