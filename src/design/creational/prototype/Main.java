package design.creational.prototype;

//sample program to show the use of prototype pattern
public class Main {
    public static void main(String[] args) {
        //create a prototype
        Shape shape = new Circle();
        //clone the prototype
        Shape clonedShape = shape.clone();
        //print the details of the cloned shape
        System.out.println("Cloned Shape: " + clonedShape.getType());
        //change the type of the cloned shape
        clonedShape.setType("Rectangle");
        //print the details of the cloned shape
        System.out.println("Cloned Shape: " + clonedShape.getType());
        //print the details of the original shape
        System.out.println("Original Shape: " + shape.getType());
    }
}

//Shape class
class Shape {
    private String type;

    public Shape() {
        this.type = "Shape";
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Shape clone() {
        return new Shape();
    }

    public String toString() {
        return this.type;
    }
}

//Circle class
class Circle extends Shape {
    public Circle() {
        //use setter
        this.setType("Circle");
    }

    public Circle clone() {
        return new Circle();
    }
}

//Rectangle class
class Rectangle extends Shape {
    public Rectangle() {
        //use setter
        this.setType("Rectangle");
    }

    public Rectangle clone() {
        return new Rectangle();
    }
}