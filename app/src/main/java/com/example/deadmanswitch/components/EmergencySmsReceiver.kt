package com.example.deadmanswitch.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.deadmanswitch.MainActivity
import com.example.deadmanswitch.data.ALARM_STATUS_KEY
import com.example.deadmanswitch.data.EMERGENCY_SMS_KEY
import com.example.deadmanswitch.data.PREFERENCES_KEY

class EmergencySmsReceiver : BroadcastReceiver() {

    private lateinit var sharedPref: SharedPreferences

    override fun onReceive(context: Context, intent: Intent) {
        sharedPref = context.getSharedPreferences(
            PREFERENCES_KEY,
            Context.MODE_PRIVATE
        )
        sharedPref.edit(true) {
            putBoolean(EMERGENCY_SMS_KEY, true)
            putBoolean(ALARM_STATUS_KEY, false)
        }
        val myIntent = Intent(context, MainActivity::class.java)
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(myIntent)
    }
}