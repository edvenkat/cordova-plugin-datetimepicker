package com.alexanderploner.datetimepicker.picker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.alexanderploner.datetimepicker.PickerOptions;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends BasePickerFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        PickerOptions options = (PickerOptions) getArguments().getSerializable("options");

        Date date = new Date();
        date.setTime(options.getDate());
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        DatePicker datePicker = datePickerDialog.getDatePicker();
        if (options.getMinDate() > 0) {
            datePicker.setMinDate(options.getMinDate());
        }
        if (options.getMaxDate() > 0) {
            datePicker.setMaxDate(options.getMaxDate());
        }
        return datePickerDialog;
    }
    public static boolean validatePastDate(Context mContext,int day,int month,int year){
            final Calendar c = Calendar.getInstance();
            int currentYear = c.get(Calendar.YEAR);
            int currentMonth = c.get(Calendar.MONTH)+1;
            int currentDay = c.get(Calendar.DAY_OF_MONTH);
            if (day > currentDay && year == currentYear && month == currentMonth) {
               // Toast.makeText(mContext, "Please select valid date", Toast.LENGTH_LONG).show();
                return false;
            } else if (month > currentMonth && year == currentYear) {
                //Toast.makeText(mContext, "Please select valid month", Toast.LENGTH_LONG).show();
                return false;
            } else if (year > currentYear) {
               // Toast.makeText(mContext, "Please select valid year", Toast.LENGTH_LONG).show();
                return false;
            }

            return true;
    }
    public static boolean validateFutureDate(Context mContext,int day,int month,int year){
            final Calendar c = Calendar.getInstance();
            int currentYear = c.get(Calendar.YEAR);
            int currentMonth = c.get(Calendar.MONTH)+1;
            int currentDay = c.get(Calendar.DAY_OF_MONTH);
            if (day < currentDay && year == currentYear && month == currentMonth) {
               // Toast.makeText(mContext, "Please select valid date", Toast.LENGTH_LONG).show();
                return false;
            } else if (month < currentMonth && year == currentYear) {
               // Toast.makeText(mContext, "Please select valid month", Toast.LENGTH_LONG).show();
                return false;
            } else if (year < currentYear) {
               // Toast.makeText(mContext, "Please select valid year", Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        boolean past = DatePickerFragment.validatePastDate('',day,month,year);
        boolean future = DatePickerFragment.validateFutureDate('',day,month,year);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        sendResult1(past);
    }

}
