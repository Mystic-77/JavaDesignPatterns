package design.creational.factorymethod;

// factory method is a design pattern that provides an interface for creating objects in a superclass,
// but hides concrete classes from the client.
// The factory method lets a class defer instantiation to subclasses.
// The client uses the factory method to get the concrete class.
// The client doesn't know the concrete class.

public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}

//create a factory class
class ShapeFactory extends ShapeFactoryBase {
    //use getShape method to get object of type shape
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        //return circle for all shapes
        //base class can use different implementations of the shape object effectively changing the method
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        }
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Circle();
        }
        if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Circle();
        }
        return null;
    }
}

//create a Shape interface
interface Shape {
    void draw();
}
//create a Circle class
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

//create a Rectangle class
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

//create a Square class
class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

//create a base class
class ShapeFactoryBase {
    //use getShape method to get object of type shape
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        }
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }
        if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
