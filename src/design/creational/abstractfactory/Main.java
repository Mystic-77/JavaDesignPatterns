package design.creational.abstractfactory;

/**
 * Explanation :
 * Assume we have a client that wants to create two types of products. These products can have multiple implementations.
 * A direct method is to declare interfaces of these two types and then implement them in the client.
 * This is not a good approach as it is not flexible.
 * If u have multiple clients that are doing this then ur code will be very hard to maintain.
 * Example : 2 lines per client. For each client u have to change the implementation of the product.
 * But using a factory and abstract factory pattern u can create a single class that can be used by multiple clients. This class will have 2 lines
 * and reduce the code by 50% on all clients and also creates a single point of change.
 * If u have a client that wants to create a product that has a specific implementation then u have to change the client code in general case.
 */
//create an abstract factory
public class Main {
    public static void main(String[] args) {
        // create a factory
        AbstractFactory factory = new ConcreteFactory1();
        //create a factory2
        AbstractFactory factory2 = new ConcreteFactory2();
        // create a product
        ProductA productA = factory.createProductA();
        ProductB productB = factory.createProductB();
        ProductA productA2 = factory2.createProductA();
        ProductB productB2 = factory2.createProductB();
        // use the product
        productA.use();
        productB.use();
        productA2.use();
        productB2.use();
    }
}
// create an abstract factory
interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}
// create concrete factories
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }
    @Override
    public ProductB createProductB() {
        return new ProductB1();
    }
}
class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductA2();
    }
    @Override
    public ProductB createProductB() {
        return new ProductB2();
    }
}
// create abstract products
interface ProductA {
    void use();
}
interface ProductB {
    void use();
}
// create concrete products
class ProductA1 implements ProductA {
    @Override
    public void use() {
        System.out.println("ProductA1");
    }
}
class ProductA2 implements ProductA {
    @Override
    public void use() {
        System.out.println("ProductA2");
    }
}
class ProductB1 implements ProductB {
    @Override
    public void use() {
        System.out.println("ProductB1");
    }
}
class ProductB2 implements ProductB {
    @Override
    public void use() {
        System.out.println("ProductB2");
    }
}