package it.near.sdk.reactions.feedback;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import it.near.sdk.BuildConfig;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class FeedbackTest {

    @Test
    public void feedbackIsParcelable() {
        Feedback feedback = new Feedback();
        feedback.setId("feedback_id");
        feedback.setRecipeId("recipe_id");
        feedback.setQuestion("what comes after five?");
        Parcel parcel = Parcel.obtain();
        feedback.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Feedback actual = Feedback.CREATOR.createFromParcel(parcel);
        assertThat(actual.getId(), is(feedback.getId()));
        assertThat(actual.getRecipeId(), is(feedback.getRecipeId()));
        assertThat(actual.getQuestion(), is(feedback.getQuestion()));
    }

}