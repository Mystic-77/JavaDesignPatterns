package design.creational.builder;
// create builder pattern example
public class Main {
    public static void main(String[] args) {
        // create director and build product
        Director director = new Director();
        Product product = director.build(new ConcreteBuilder());
        // show product
        System.out.println(product.toString());
    }
}
// create director class
class Director {
    public Product build(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
// create Builder interface
interface Builder {
    void buildPartA();
    void buildPartB();
    void buildPartC();
    Product getResult();
}
// create ConcreteBuilder class
class ConcreteBuilder implements Builder {
    private Product product = new Product();
    public void buildPartA() {
        product.add("PartA");
    }
    public void buildPartB() {
        product.add("PartB");
    }
    public void buildPartC() {
        product.add("PartC");
    }
    public Product getResult() {
        return product;
    }
}
// create Product class
class Product {
    private String partX;
    private String partY;
    private String partZ;
    public void add(String part) {
        if (partX == null) {
            partX = part;
        } else if (partY == null) {
            partY = part;
        } else if (partZ == null) {
            partZ = part;
        }
    }
    @Override
    public String toString() {
        return "Product{" +
                "partX='" + partX + '\'' +
                ", partY='" + partY + '\'' +
                ", partZ='" + partZ + '\'' +
                '}';
    }
}
// Output:
// Product{partX='PartA', partY='PartB', partZ='PartC'}