package lilap.com.goalset.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Calendar;
import java.util.Date;

import lilap.com.goalset.R;
import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.entity.goal.Goal;

public class AddGoalActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener {
    private Button addBtn;
    private EditText titleEdt;
    private Spinner spinner;
    private EditText descriptionEdt;
    private SwitchCompat dateSwitch;
    private RatingBar priorityBar;
    private Calendar calendar;
    private static Date currDate;
    private static int period;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_goal);
        addBtn = (Button)findViewById(R.id.AddBtn);
        titleEdt = (EditText)findViewById(R.id.titleEdt);
        dateSwitch = (SwitchCompat)findViewById(R.id.switch1);
        descriptionEdt = (EditText)findViewById(R.id.editText);
        priorityBar = (RatingBar)findViewById(R.id.ratingBar);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.goalsType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    period = 1;
                } else {
                    period = 7;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                period = 1;
            }
        });


        dateSwitch.setOnCheckedChangeListener(this);
        addBtn.setOnClickListener(this);

        currDate = (Date)getIntent().getExtras().get("date");
        calendar = Calendar.getInstance();
        calendar.setTime(currDate);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,myListner,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

            datePickerDialog.show();
        }
        else{
            calendar.setTime(currDate);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AddBtn:
                Intent intent = new Intent();
                Goal goal = new Goal();
                goal.setTitle(titleEdt.getText().toString());
                goal.setDescription(descriptionEdt.getText().toString());
                goal.setPriority((int) priorityBar.getRating());
                goal.setDate(calendar.getTime());
                goal.setPeriod(period);
                goal.setProgress(0);
                DaoFactory.getDaoFactory(this).getGoalDao().add(goal);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    DatePickerDialog.OnDateSetListener myListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
           calendar.set(year,monthOfYear,dayOfMonth);
        }
    };
}
