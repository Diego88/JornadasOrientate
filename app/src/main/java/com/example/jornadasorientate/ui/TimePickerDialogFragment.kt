package com.example.jornadasorientate.ui

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(
            activity,
            activity as TimePickerDialog.OnTimeSetListener,
            hour,
            minute,
            true)
    }
}
