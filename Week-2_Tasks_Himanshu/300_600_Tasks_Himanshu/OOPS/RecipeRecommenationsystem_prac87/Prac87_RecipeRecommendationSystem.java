package RecipeRecommenationsystem_prac87;

import java.util.ArrayList;
import java.util.List;

class Ingredient {
    public String name;

    public Ingredient(String ingredientName) {
        name = ingredientName;
    }

    // Define equality method for Ingredient
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Ingredient other = (Ingredient) obj;
        return name.equals(other.name);
    }
}

class Recipe {
    public String name;
    public List<Ingredient> ingredients;

    public Recipe(String recipeName, List<Ingredient> recipeIngredients) {
        name = recipeName;
        ingredients = new ArrayList<>(recipeIngredients);
    }

    public boolean containsIngredient(Ingredient ingredient) {
        return ingredients.contains(ingredient);
    }
}

class User {
    public String name;
    public List<Ingredient> dietaryRestrictions;
    public List<Recipe> favoriteRecipes;

    public User(String userName) {
        name = userName;
        dietaryRestrictions = new ArrayList<>();
        favoriteRecipes = new ArrayList<>();
    }

    public void addDietaryRestriction(Ingredient restriction) {
        dietaryRestrictions.add(restriction);
    }

    public void addFavoriteRecipe(Recipe recipe) {
        favoriteRecipes.add(recipe);
    }

    public List<Recipe> recommendRecipes(List<Recipe> allRecipes) {
        List<Recipe> recommendedRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            // Check if the recipe contains any restricted ingredient
            boolean hasRestrictedIngredient = dietaryRestrictions.stream()
                    .anyMatch(restriction -> recipe.containsIngredient(restriction));

            // Skip recipes with restricted ingredients
            if (!hasRestrictedIngredient) {
                recommendedRecipes.add(recipe);
            }
        }

        return recommendedRecipes;
    }
}

public class Prac87_RecipeRecommendationSystem {
    public static void main(String[] args) {
        // Create ingredients
        Ingredient tomato = new Ingredient("Tomato");
        Ingredient onion = new Ingredient("Onion");
        Ingredient chicken = new Ingredient("Chicken");

        // Create recipes
        Recipe salad = new Recipe("Salad", List.of(tomato, onion));
        Recipe pasta = new Recipe("Pasta", List.of(tomato, onion));
        Recipe chickenSoup = new Recipe("Chicken Soup", List.of(chicken, onion));

        // Create a user
        User user = new User("Alice");
        user.addDietaryRestriction(chicken);

        // Add favorite recipes to the user
        user.addFavoriteRecipe(salad);
        user.addFavoriteRecipe(pasta);

        // Get recipe recommendations for the user
        List<Recipe> allRecipes = List.of(salad, pasta, chickenSoup);
        List<Recipe> recommendedRecipes = user.recommendRecipes(allRecipes);

        // Display recommended recipes
        System.out.println("Recommended Recipes for " + user.name + ":");
        for (Recipe recipe : recommendedRecipes) {
            System.out.println("- " + recipe.name);
        }
    }
}
