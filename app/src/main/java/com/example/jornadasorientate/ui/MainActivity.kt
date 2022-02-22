package com.example.jornadasorientate.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import com.example.jornadasorientate.R
import com.example.jornadasorientate.receivers.AlarmBroadcastReceiver
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private val alarmTextView: TextView by lazy {
        findViewById(R.id.alarmTextView)
    }

    private val alarmSwitch: Switch by lazy {
        findViewById(R.id.alarmSwitch)
    }

    private var isEnabledFromPicker = false

    private val timeFormatter = SimpleDateFormat("HH:mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListeners()
    }

    override fun onTimeSet(timePicker: TimePicker?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        alarmTextView.text = timeFormatter.format(calendar.time)
        startAlarm(calendar)

        isEnabledFromPicker = true
        alarmSwitch.isChecked = true
    }

    private fun setupListeners() {
        alarmTextView.setOnClickListener {
            showTimerPickerFragment()
        }

        alarmSwitch.setOnCheckedChangeListener { button, _ ->
            if (!isEnabledFromPicker) {
                if (button.isChecked) {
                    val alarmText = alarmTextView.text
                    val hour = alarmText.substring(0, alarmText.indexOf(":")).toInt()
                    val minute = alarmText.substring(alarmText.indexOf(":") + 1, alarmText.length).toInt()
                    onTimeSet(null, hour, minute)
                } else {
                    cancelAlarm()
                }
            }

            isEnabledFromPicker = false
        }
    }

    private fun showTimerPickerFragment() {
        val timePickerFragment = TimePickerDialogFragment()
        timePickerFragment.show(supportFragmentManager, "time_picker")
    }

    private fun cancelAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.cancel(pendingIntent)

        showToast("Alarma apagada")
    }

    private fun startAlarm(calendar: Calendar) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        val time = timeFormatter.format(calendar.time)
        showToast("La alarma sonará a las $time")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
