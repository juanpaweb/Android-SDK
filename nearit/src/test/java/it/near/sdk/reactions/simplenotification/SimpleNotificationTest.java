package it.near.sdk.reactions.simplenotification;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import it.near.sdk.BuildConfig;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SimpleNotificationTest {

    @Test
    public void simpleNotificationIsParcelable() {
        SimpleNotification simpleNotification = new SimpleNotification("message", "title");
        Parcel parcel = Parcel.obtain();
        simpleNotification.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        SimpleNotification actual = SimpleNotification.CREATOR.createFromParcel(parcel);
        assertThat(actual.getNotificationMessage(), is(simpleNotification.getNotificationMessage()));
        assertThat(actual.getNotificationTitle(), is(simpleNotification.getNotificationTitle()));


        SimpleNotification simpleNotificationNoTitle = new SimpleNotification("message", null);
        parcel = Parcel.obtain();
        simpleNotificationNoTitle.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        actual = SimpleNotification.CREATOR.createFromParcel(parcel);
        assertThat(actual.getNotificationMessage(), is(simpleNotification.getNotificationMessage()));
        assertThat(actual.getNotificationTitle(), is(nullValue()));
    }
}