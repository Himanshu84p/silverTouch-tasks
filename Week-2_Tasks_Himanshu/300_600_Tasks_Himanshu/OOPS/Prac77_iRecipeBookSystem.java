import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Ingredient {
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Recipe {
    private String name;
    private List<Ingredient> ingredients;
    private String category;

    public Recipe(String name, List<Ingredient> ingredients, String category) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public String getCategory() {
        return category;
    }
}

class Cookbook {
    private List<Recipe> recipes;

    public Cookbook() {
        this.recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public List<Recipe> searchRecipesByIngredient(Ingredient ingredient) {
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().contains(ingredient))
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipesByCategory(String category) {
        return recipes.stream()
                .filter(recipe -> recipe.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}

public class Prac77_iRecipeBookSystem {
    public static void main(String[] args) {
        // Create ingredients
        Ingredient chicken = new Ingredient("Chicken");
        Ingredient pasta = new Ingredient("Pasta");
        Ingredient tomatoSauce = new Ingredient("Tomato Sauce");
        Ingredient cheese = new Ingredient("Cheese");

        // Create recipes
        Recipe pastaBake = new Recipe("Pasta Bake", List.of(pasta, chicken, tomatoSauce, cheese), "Baked");
        Recipe chickenAlfredo = new Recipe("Chicken Alfredo", List.of(pasta, chicken, cheese), "Creamy");

        // Create cookbook
        Cookbook cookbook = new Cookbook();

        // Add recipes to the cookbook
        cookbook.addRecipe(pastaBake);
        cookbook.addRecipe(chickenAlfredo);

        // Search for recipes based on ingredient
        List<Recipe> chickenRecipes = cookbook.searchRecipesByIngredient(chicken);
        System.out.println("Recipes with Chicken:");
        for (Recipe recipe : chickenRecipes) {
            System.out.println(recipe.getName());
        }

        // Search for recipes based on category
        List<Recipe> creamyRecipes = cookbook.getRecipesByCategory("Creamy");
        System.out.println("\nCreamy Recipes:");
        for (Recipe recipe : creamyRecipes) {
            System.out.println(recipe.getName());
        }
    }
}
