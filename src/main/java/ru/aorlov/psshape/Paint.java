package ru.aorlov.psshape;

public class Paint {
    Shape shape;

    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public String executeShape() {
        return this.shape.draw();
    }
}
