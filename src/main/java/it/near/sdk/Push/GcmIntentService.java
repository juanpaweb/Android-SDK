package it.near.sdk.Push;

import android.content.Intent;

import it.near.sdk.R;
import it.near.sdk.Reactions.Content.Content;
import it.near.sdk.Reactions.CoreContentsListener;
import it.near.sdk.Reactions.Poll.Poll;
import it.near.sdk.Utils.BaseIntentService;
import it.near.sdk.Utils.NearNotification;

/**
 * @author cattaneostefano.
 */
public class GcmIntentService extends BaseIntentService implements CoreContentsListener {

    private static String TAG = "GcmIntentService";
    private static final int PUSH_NOTIFICATION_ID = 2;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public GcmIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        parseCoreContents(intent, this);
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    @Override
    public void getPollNotification(Intent intent, Poll notification, String notifText, String content_plugin, String content_action, String pulse_plugin, String pulse_action, String pulse_bundle) {
        Intent targetIntent = getPackageManager().getLaunchIntentForPackage(this.getPackageName());
        targetIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        targetIntent.putExtras(intent.getExtras());
        String notif_title = intent.getStringExtra("notif_title");
        if (notif_title == null) {
            notif_title = getApplicationInfo().loadLabel(getPackageManager()).toString();
        }
        NearNotification.send(this, R.drawable.ic_send_white_24dp, notif_title, notifText, targetIntent, PUSH_NOTIFICATION_ID);
    }

    @Override
    public void getContentNotification(Intent intent, Content notification, String notifText, String content_plugin, String content_action, String pulse_plugin, String pulse_action, String pulse_bundle) {
        Intent targetIntent = getPackageManager().getLaunchIntentForPackage(this.getPackageName());
        targetIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        targetIntent.putExtras(intent.getExtras());
        String notif_title = intent.getStringExtra("notif_title");
        if (notif_title == null) {
            notif_title = getApplicationInfo().loadLabel(getPackageManager()).toString();
        }
        NearNotification.send(this, R.drawable.ic_send_white_24dp, notif_title, notifText, targetIntent, PUSH_NOTIFICATION_ID);

    }

}
