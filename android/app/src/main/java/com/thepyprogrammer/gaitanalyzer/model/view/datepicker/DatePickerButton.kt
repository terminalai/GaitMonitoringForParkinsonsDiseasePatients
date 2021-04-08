package com.thepyprogrammer.gaitanalyzer.model.view.datepicker

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*


class DatePickerButton(context: Context, attributeSet: AttributeSet) :
        androidx.appcompat.widget.AppCompatButton(context, attributeSet) {

    companion object {
        val dTF: DateTimeFormatter =
                DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd/MM/yyyy")
                        .toFormatter()
    }

    init {
        this.text = "Select Date"
    }

    fun setOnClickListener(
            originalDate: LocalDate = LocalDate.now(),
            minDate: Long? = null,
            maxDate: Long? = null,
            action: LocalDate.() -> Unit
    ) {
        super.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, monthOfYear, dayOfMonth ->
                        val dateSelected = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
                        dateSelected.action()
                        this@DatePickerButton.text = dTF.format(dateSelected)
                    },
                    originalDate.year, originalDate.monthValue - 1, originalDate.dayOfMonth
            )
            if (minDate != null) datePickerDialog.datePicker.minDate = minDate
            if (maxDate != null) datePickerDialog.datePicker.maxDate = maxDate

            datePickerDialog.show()
        }
    }

}