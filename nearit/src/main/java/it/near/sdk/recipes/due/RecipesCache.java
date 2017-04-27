package it.near.sdk.recipes.due;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.near.sdk.recipes.models.Recipe;

public class RecipesCache {

    private static final String KEY_RECIPE_LIST_CACHE = "recipe_list";
    private static final String RECIPE_CACHE_PREFS_NAME = "NearRecipeCache";
    private final SharedPreferences sp;

    public RecipesCache(SharedPreferences sp) {
        this.sp = sp;
    }

    public List<Recipe> getCachedRecipes() {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Recipe>>() {
        }.getType();
        return gson.<ArrayList<Recipe>>fromJson(sp.getString(KEY_RECIPE_LIST_CACHE, ""), collectionType);
    }

    public void persistRecipeList(List<Recipe> recipes) {
        Gson gson = new Gson();
        String listStringified = gson.toJson(recipes);
        sp.edit()
                .putString(KEY_RECIPE_LIST_CACHE, listStringified)
                .apply();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(RECIPE_CACHE_PREFS_NAME, Context.MODE_PRIVATE);
    }
}
