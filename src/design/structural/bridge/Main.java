package design.structural.bridge;

public class Main {
    public static void main(String[] args) {
        Abstraction abstraction1 = new Abstraction1(new Implementor1());
        abstraction1.operation();
    }
}

abstract class Abstraction {
    Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}

abstract class Implementor {
    public abstract void operationImpl();
}

//Abstraction1
class Abstraction1 extends Abstraction {
    public Abstraction1(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        implementor.operationImpl();
    }
}

//implementor1
class Implementor1 extends Implementor {
    @Override
    public void operationImpl() {
        System.out.println("Implementor1");
    }
}
