class Pet {
    private String name;
    private int health;
    private int happiness;

    public Pet(String petName) {
        this.name = petName;
        this.health = 100;
        this.happiness = 100;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getHappiness() {
        return happiness;
    }

    public void feed(int foodValue) {
        health += foodValue;
        happiness += 5;
        System.out.println(name + " has been fed. Health + " + foodValue + ", Happiness + 5");
    }

    public void play() {
        happiness += 10;
        System.out.println(name + " is playing. Happiness + 10");
    }

    public void showStatus() {
        System.out.println("Status of " + name + ":");
        System.out.println("Health: " + health);
        System.out.println("Happiness: " + happiness);
    }
}

class Food {
    private String name;
    private int nutritionValue;

    public Food(String foodName, int nutrition) {
        this.name = foodName;
        this.nutritionValue = nutrition;
    }

    public String getName() {
        return name;
    }

    public int getNutritionValue() {
        return nutritionValue;
    }
}

class Owner {
    private String name;

    public Owner(String ownerName) {
        this.name = ownerName;
    }

    public String getName() {
        return name;
    }

    public void feedPet(Pet pet, Food food) {
        pet.feed(food.getNutritionValue());
    }

    public void playWithPet(Pet pet) {
        pet.play();
    }
}

public class Prac95 {
    public static void main(String[] args) {
        Pet myPet = new Pet("Buddy");
        Food petFood = new Food("Dog Food", 20);
        Owner petOwner = new Owner("John");

        petOwner.feedPet(myPet, petFood);
        petOwner.playWithPet(myPet);

        myPet.showStatus();
    }
}
