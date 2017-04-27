package it.near.sdk.recipes.due;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.near.sdk.GlobalConfig;
import it.near.sdk.reactions.Reaction;
import it.near.sdk.recipes.RecipeCooler;
import it.near.sdk.recipes.RecipeRefreshListener;
import it.near.sdk.recipes.models.Recipe;
import it.near.sdk.trackings.TrackManager;

public class RecipeManager2 {

    private final RecipesRepository recipesRepository;
    private final GlobalConfig globalConfig;
    private final RecipeCooler recipeCooler;
    private final TrackManager trackManager;

    private List<Reaction> reactions = new CopyOnWriteArrayList<>();

    public RecipeManager2(RecipesRepository recipesRepository, GlobalConfig globalConfig, RecipeCooler recipeCooler, TrackManager trackManager) {
        this.recipesRepository = recipesRepository;
        this.globalConfig = globalConfig;
        this.recipeCooler = recipeCooler;
        this.trackManager = trackManager;
    }

    public List<Recipe> getRecipes() {
        return null;
    }

    public void refreshConfig() {
        refreshConfig(new RecipeRefreshListener() {
            @Override
            public void onRecipesRefresh() {

            }

            @Override
            public void onRecipesRefreshFail() {

            }
        });
    }

    public void refreshConfig(final RecipeRefreshListener listener) {
        recipesRepository.refreshList(listener);
    }

    public void addReaction(Reaction reaction) {
        reactions.add(reaction);
    }

    public void gotPulse(String plugin, String action, String bundle) {

    }

    public boolean fireRecipe(Recipe recipe) {
        return false;
    }

    public void processRecipe(String id) {

    }
}
