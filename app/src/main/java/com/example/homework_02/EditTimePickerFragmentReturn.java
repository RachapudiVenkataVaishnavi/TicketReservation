package com.example.homework_02;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.text.format.DateFormat;

import android.widget.TimePicker;


import java.util.Calendar;



public  class EditTimePickerFragmentReturn extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));




    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        Calendar calendar = Calendar.getInstance();



        if(hourOfDay < 12 && hourOfDay >= 0)
        {


            EditTicketActivity.editText_returnTime.setText(hourOfDay + ":" + minute+""+"AM" );

        }
        else {
            hourOfDay -= 12;
            if(hourOfDay == 0)
            {
                hourOfDay = 12;
            }


            EditTicketActivity.editText_returnTime.setText(hourOfDay + ":" + minute+""+"PM" );
        }



    }
}
