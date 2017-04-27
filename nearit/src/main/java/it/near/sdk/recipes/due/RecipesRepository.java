package it.near.sdk.recipes.due;

import android.net.Uri;

import java.util.List;

import it.near.sdk.communication.Constants;
import it.near.sdk.communication.NearAsyncHttpClient;
import it.near.sdk.morpheusnear.Morpheus;
import it.near.sdk.recipes.RecipeRefreshListener;
import it.near.sdk.recipes.models.OperationAction;
import it.near.sdk.recipes.models.PulseAction;
import it.near.sdk.recipes.models.PulseBundle;
import it.near.sdk.recipes.models.ReactionAction;
import it.near.sdk.recipes.models.ReactionBundle;
import it.near.sdk.recipes.models.Recipe;

public class RecipesRepository {

    private static final String RECIPES_PATH = "recipes";
    private static final String PROCESS_PATH = "process";
    private final NearAsyncHttpClient httpClient;
    private final RecipesCache recipesCache;
    private Morpheus morpheus;

    private List<Recipe> recipes;

    public RecipesRepository(NearAsyncHttpClient httpClient, RecipesCache recipesCache) {
        this.httpClient = httpClient;
        this.recipesCache = recipesCache;
        setUpMorpheusParser();
    }

    private void setUpMorpheusParser() {
        morpheus = new Morpheus();
        // register your resources
        morpheus.getFactory().getDeserializer().registerResourceClass("recipes", Recipe.class);
        morpheus.getFactory().getDeserializer().registerResourceClass("pulse_actions", PulseAction.class);
        morpheus.getFactory().getDeserializer().registerResourceClass("operation_actions", OperationAction.class);
        morpheus.getFactory().getDeserializer().registerResourceClass("reaction_actions", ReactionAction.class);
        morpheus.getFactory().getDeserializer().registerResourceClass("pulse_bundles", PulseBundle.class);
        morpheus.getFactory().getDeserializer().registerResourceClass("reaction_bundles", ReactionBundle.class);
    }

    public void refreshList() {
        refreshList(new RecipeRefreshListener() {
            @Override
            public void onRecipesRefresh() {

            }

            @Override
            public void onRecipesRefreshFail() {

            }
        });
    }
    public void refreshList(RecipeRefreshListener listener) {
        Uri url = Uri.parse(RECIPES_PATH).buildUpon()
                .appendPath(PROCESS_PATH).build();
    }

    public List<Recipe> getRecipes() {
        return null;
    }

    public void fetchRecipe(String id, RecipeListener listener) {

    }

    public void evaluateFromPulse(String plugin, String action, String bundle) {

    }

    public interface RecipeListener {
        void gotRecipe(Recipe recipe);
        void onFail(String error);
    }
}
