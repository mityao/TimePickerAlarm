package com.willyao.android.timepickeralarm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.willyao.android.timepickeralarm.Model.Todo;
import com.willyao.android.timepickeralarm.Utils.AlarmUtils;
import com.willyao.android.timepickeralarm.Utils.DataUtils;

import java.util.Calendar;
import java.util.Date;

public class EditAlarmActivity extends AppCompatActivity {
    private Todo todo;
    private Date remindDate;

    private TextView dateTv;
    private TextView timeTv;
    private Button save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alarm);

        //opened by MainActivvity
        Intent intent = getIntent();

        setupUI();
    }
    private void setupUI() {
        dateTv = (TextView) findViewById(R.id.date_datail_set);
        timeTv = (TextView) findViewById(R.id.time_detail_set);
        save = (Button) findViewById(R.id.button);

        if (remindDate != null) {
            dateTv.setText(DataUtils.dateToStringDate(remindDate));
            timeTv.setText(DataUtils.dateToStringTime(remindDate));
        } else {
            dateTv.setText(R.string.set_date);
            timeTv.setText(R.string.set_date);
        }
        setupDatePicker();
    }

    private void setupDatePicker() {
        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = getCalendarFromRemindDate();
                Dialog dialog = new DatePickerDialog(
                        EditAlarmActivity.this,
                        (DatePickerDialog.OnDateSetListener) EditAlarmActivity.this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        timeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = getCalendarFromRemindDate();
                Dialog dialog = new TimePickerDialog(
                        EditAlarmActivity.this,
                        (TimePickerDialog.OnTimeSetListener) EditAlarmActivity.this,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true);
                dialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmUtils.setAlarm(EditAlarmActivity.this, remindDate);
            }
        });
    }

    private Calendar getCalendarFromRemindDate() {
        Calendar c = Calendar.getInstance();
        if (remindDate != null) {
            c.setTime(remindDate);
        }
        return c;
    }

}
