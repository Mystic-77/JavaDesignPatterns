package design.structural.adapter;

//example of both object and class adapter using target interface and adaptee class
public class Main {
    public static void main(String[] args) {
        //create object adapter
        ObjectAdapter adapter = new ObjectAdapter();
        //use object adapter
        adapter.useObjectAdapter();
        //create class adapter
        ClassAdapter adapter2 = new ClassAdapter();
        //use class adapter
        adapter2.useClassAdapter();
    }
}

//target interface
interface Target {
    void request();
}

//adaptee class
class Adaptee {
    public void specificRequest() {
        System.out.println("specific request");
    }
}

//object adapter
class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter() {
        adaptee = new Adaptee();
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }

    public void useObjectAdapter() {
        request();
    }
}

//class adapter
class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest();
    }

    public void useClassAdapter() {
        request();
    }

    public void specificRequest() {
        System.out.println("specific request");
    }
}