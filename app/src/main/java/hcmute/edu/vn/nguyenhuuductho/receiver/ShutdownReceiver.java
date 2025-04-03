package hcmute.edu.vn.nguyenhuuductho.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import hcmute.edu.vn.nguyenhuuductho.util.Preferences;


public class ShutdownReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SHUTDOWN.equals(intent.getAction())) {
            Preferences preferences = new Preferences(context);

            preferences.setAlarmPending(false);
            preferences.setSnoozeAlarmPending(false);
            preferences.setAlarmRinging(false);
        }
    }
}