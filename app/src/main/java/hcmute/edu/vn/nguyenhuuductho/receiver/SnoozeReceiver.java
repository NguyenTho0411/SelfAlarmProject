package hcmute.edu.vn.nguyenhuuductho.receiver;


import static hcmute.edu.vn.nguyenhuuductho.util.AlarmSetter.SNOOZE_ALARM;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



import java.util.Calendar;

import hcmute.edu.vn.nguyenhuuductho.util.AlarmSetter;
import hcmute.edu.vn.nguyenhuuductho.util.Preferences;

public class SnoozeReceiver extends BroadcastReceiver {

    private Preferences mPreferences;

    public SnoozeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mPreferences = new Preferences(context);
        AlarmSetter alarmSetter = new AlarmSetter();

        setSnoozePreferences();

        AlarmSetter.cancelAlarm(context, SNOOZE_ALARM);

        alarmSetter.schedule(context, SNOOZE_ALARM);

        if (mPreferences.getSnoozeNumberLeft() != 0)
            mPreferences.setSnoozeNumberLeft(mPreferences.getSnoozeNumberLeft() - 1);
        mPreferences.setSnoozeAlarmPending(true);
    }

    private void setSnoozePreferences() {
        Calendar snoozeCalendar = Calendar.getInstance();
        snoozeCalendar.setTimeInMillis(System.currentTimeMillis());
        snoozeCalendar.set(Calendar.MINUTE,
                snoozeCalendar.get(Calendar.MINUTE) + mPreferences.getSnoozeDuration());

        mPreferences.setSnoozeAlarmTime(
                snoozeCalendar.get(Calendar.HOUR_OF_DAY), snoozeCalendar.get(Calendar.MINUTE));
    }
}