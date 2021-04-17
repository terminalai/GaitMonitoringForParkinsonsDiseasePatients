package com.thepyprogrammer.gaitanalyzer.model.view.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}