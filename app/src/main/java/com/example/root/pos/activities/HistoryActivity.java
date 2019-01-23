package com.example.root.pos.activities;

import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;

import com.example.root.pos.R;
import com.example.root.pos.adapter.HistoryAdapter;
import com.example.root.pos.model.SalesHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_list)
    RecyclerView recyclerView;



    @BindView(R.id.edt_start_date)
    TextInputEditText edt_start_date;
    @BindView(R.id.edt_end_date)
    TextInputEditText edt_end_date;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private String startDate = "";
    private String endDate = "";



    private HistoryAdapter mAdapter;
    private ArrayList<SalesHistory> salesHistoryList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        setTitle(" Sales  History");
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_clear_all_black_24dp);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);


        final String currentDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String currentDateFormatted = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());

        startDate = currentDate;
        endDate = currentDate;

        edt_start_date.setText(currentDateFormatted);
        edt_end_date.setText(currentDateFormatted);

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy");




        mAdapter = new HistoryAdapter(salesHistoryList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        setDatePickerField();

        edt_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });
        edt_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog.show();
            }
        });



    }

    private void setDatePickerField() {

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                String monthString = String.valueOf(monthOfYear+1);
                if (monthString.length() == 1) {
                    monthString = "0" + monthString;
                }
                String dayString = String.valueOf(dayOfMonth);
                if (dayString.length() == 1) {
                    dayString = "0" + dayString;
                }
                startDate = year+monthString+dayString;
                edt_start_date.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String monthString = String.valueOf(monthOfYear+1);
                if (monthString.length() == 1) {
                    monthString = "0" + monthString;
                }
                String dayString = String.valueOf(dayOfMonth);
                if (dayString.length() == 1) {
                    dayString = "0" + dayString;
                }
                endDate = year+monthString+dayString;
                edt_end_date.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }



}
