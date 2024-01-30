package AnimalFold;

import java.util.ArrayList;
import java.util.List;

class Animal {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Animal [ Name = " + name + "]";
    }
}

class AnimalCollection implements Cloneable {
    private String animalCollectionName;
    List<Animal> animals = new ArrayList<>();

    public String getCollectionName() {
        return animalCollectionName;
    }

    public void setCollectionName(String name) {
        this.animalCollectionName = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void loadAnimal() {
        String[] str = { "Dog", "Cheetah", "Lion" };

        for (int i = 0; i < str.length; i++) {
            Animal animal = new Animal();
            animal.setName(str[i]);
            getAnimals().add(animal);
        }
    }

    @Override
    protected AnimalCollection clone() throws CloneNotSupportedException {
        AnimalCollection animalcollection = new AnimalCollection();
        for (Animal obj : this.getAnimals()) {
            animalcollection.getAnimals().add(obj);
        }
        return animalcollection;
    }

    @Override
    public String toString() {
        return "AnimalCollection [AnimalCollection Name = " + animalCollectionName + ", Animals = " + animals + " ]";
    }

}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        // create an instance of the AnimalCollection class
        AnimalCollection animalCollection = new AnimalCollection();

        animalCollection.setCollectionName("First Animal Collection");
        animalCollection.loadAnimal();

        AnimalCollection animalCollection1 = animalCollection.clone(); // Deep CLonning
        animalCollection1.setCollectionName("Second Animal Collection");

        System.out.println(animalCollection);
        System.out.println(animalCollection1);
    }
}
