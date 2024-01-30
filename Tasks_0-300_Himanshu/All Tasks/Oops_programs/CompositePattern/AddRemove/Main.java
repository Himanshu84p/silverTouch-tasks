package AddRemove;

import java.util.ArrayList;
import java.util.List;

interface Component {
    void operation();

    void add(Component component);

    void remove(Component component);
}

class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Leaf " + name + " operation");
    }

    @Override
    public void add(Component component) {

        System.out.println("Cannot add to a leaf.");
    }

    @Override
    public void remove(Component component) {

        System.out.println("Cannot remove from a leaf.");
    }
}

class Composite implements Component {
    private List<Component> components = new ArrayList<>();
    private String name;

    public Composite(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Composite " + name + " operation");
        for (Component component : components) {
            component.operation();
        }
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }
}

public class Main {
    public static void main(String[] args) {

        Component leaf1 = new Leaf("Leaf 1");
        Component leaf2 = new Leaf("Leaf 2");

        Composite composite = new Composite("Composite 1");

        composite.add(leaf1);
        composite.add(leaf2);

        Composite composite2 = new Composite("Composite 2");

        composite2.add(composite);
        composite2.add(new Leaf("Leaf 3"));

        composite.operation();
        System.out.println("------------------");
        composite2.operation();
    }
}