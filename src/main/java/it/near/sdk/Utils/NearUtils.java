package it.near.sdk.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.near.sdk.MorpheusNear.JsonApiObject;
import it.near.sdk.MorpheusNear.Morpheus;
import it.near.sdk.MorpheusNear.Resource;
import it.near.sdk.Reactions.Content.Content;
import it.near.sdk.Reactions.Coupon.Coupon;
import it.near.sdk.Reactions.CustomJSON.CustomJSON;
import it.near.sdk.Reactions.Feedback.Feedback;
import it.near.sdk.Reactions.Poll.Poll;
import it.near.sdk.Reactions.SimpleNotification.SimpleNotification;
import it.near.sdk.Recipes.Models.Recipe;

/**
 * Near utilities
 * @author cattaneostefano
 */
public class NearUtils {

    /**
     * Decode base 64 string
     * @param encoded encoded string
     * @return decoded string
     */
    public static String decodeString(String encoded) throws NullPointerException{
        byte[] dataDec = Base64.decode(encoded, Base64.DEFAULT);
        String decodedString = "";
        try {
            decodedString = new String(dataDec, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }


    /**
     * Compute app Id from the Apptoken (apikey)
     * @param apiKey token
     * @return the App Id as defined in our servers
     */
    public static String fetchAppIdFrom(String apiKey) {
        String secondSegment = substringBetween(apiKey, ".",".");
        String appId = "";
        try {
            String decodedAK = decodeString(secondSegment);
            JSONObject jwt = new JSONObject(decodedAK);
            JSONObject account = jwt.getJSONObject("data").getJSONObject("account");
            appId = account.getString("id");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("NearITErrors", "Error while processing NearIT API token. Please check if you are using the correct key.");
        }
        return appId;
    }

    /**
     * String utility method
     * @param str initial string
     * @param open
     * @param close
     * @return
     */
    private static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    /**
     * Utility method for automatically casting content. It notifies the listener if the intent contains a recognized core content.
     * @param intent The intent to analyze.
     * @param listener Contains a callback method for each content type.
     * @return true if the content was recognized as core and passed to a callback method, false if it wasn't.
     */
    public static boolean parseCoreContents(Intent intent, CoreContentsListener listener) {
        String reaction_plugin = intent.getExtras().getString("reaction-plugin");
        String recipeId = intent.getStringExtra(IntentConstants.RECIPE_ID);

        boolean coreContent = false;
        if (reaction_plugin == null) return false;
        switch (reaction_plugin) {
            case "content-notification" :
                Content c_notif = intent.getParcelableExtra("content");
                listener.gotContentNotification(intent, c_notif, recipeId);
                coreContent = true;
                break;
            case "simple-notification" :
                SimpleNotification s_notif = intent.getParcelableExtra("content");
                listener.gotSimpleNotification(intent, s_notif, recipeId);
                coreContent = true;
                break;
            case "poll-notification" :
                Poll p_notif = intent.getParcelableExtra("content");
                listener.gotPollNotification(intent, p_notif, recipeId);
                coreContent = true;
                break;
            case "coupon-blaster" :
                Coupon coup_notif = intent.getParcelableExtra("content");
                listener.gotCouponNotification(intent, coup_notif, recipeId);
                coreContent = true;
                break;
            case "json-sender" :
                CustomJSON custom_notif = intent.getParcelableExtra("content");
                listener.gotCustomJSONNotification(intent, custom_notif, recipeId);
                coreContent = true;
                break;
            case "feedbacks" :
                Feedback f_notif = intent.getParcelableExtra("content");
                listener.gotFeedbackNotification(intent, f_notif, recipeId);
                coreContent = true;
                break;
        }
        return coreContent;
    }


    /**
     * Parse a parcelable content received from a proximity receiver. Since there's no intent, the intent field of the listener callback methods is always null.
     * @param content the @{@link Parcelable} content to parse. This contains the object set in the what component of the recipe.
     * @param recipe the recipe object.
     * @param listener the listener for the casted content types.
     * @return true if the content was recognized as core and passed to a callback method, false if it wasn't.
     */
    public static boolean parseCoreContents(Parcelable content, Recipe recipe, CoreContentsListener listener) {
        String reaction_plugin = recipe.getReaction_plugin_id();
        String recipeId = recipe.getId();

        boolean coreContent = false;
        if (reaction_plugin == null) return false;
        switch (reaction_plugin) {
            case "content-notification" :
                Content c_notif = (Content) content;
                listener.gotContentNotification(null, c_notif, recipeId);
                coreContent = true;
                break;
            case "simple-notification" :
                SimpleNotification s_notif = (SimpleNotification) content;
                listener.gotSimpleNotification(null, s_notif, recipeId);
                coreContent = true;
                break;
            case "poll-notification" :
                Poll p_notif = (Poll) content;
                listener.gotPollNotification(null, p_notif, recipeId);
                coreContent = true;
                break;
            case "coupon-blaster" :
                Coupon coup_notif = (Coupon) content;
                listener.gotCouponNotification(null, coup_notif, recipeId);
                coreContent = true;
                break;
            case "json-sender" :
                CustomJSON custom_notif = (CustomJSON) content;
                listener.gotCustomJSONNotification(null, custom_notif, recipeId);
                coreContent = true;
                break;
            case "feedbacks" :
                Feedback f_notif = (Feedback) content;
                listener.gotFeedbackNotification(null, f_notif, recipeId);
                coreContent = true;
                break;
        }
        return coreContent;

    }


}
