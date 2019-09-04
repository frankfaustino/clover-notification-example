package com.frankfaustino.notifcationexample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.clover.sdk.v1.app.AppNotification;
import com.clover.sdk.v1.app.AppNotificationReceiver;

public class NotificationReceiver extends AppNotificationReceiver {
    public final static String TEST_NOTIFICATION = "test_notification";

    public NotificationReceiver() {}

    @Override
    public void onReceive(Context context, AppNotification notification) {
        Log.d("🔮️", notification.appEvent);

        if (notification.appEvent.equals(TEST_NOTIFICATION)) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(MainActivity.EXTRA_PAYLOAD, notification.payload);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
