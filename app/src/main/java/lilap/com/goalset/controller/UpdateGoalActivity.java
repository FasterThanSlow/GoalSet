package lilap.com.goalset.controller;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import lilap.com.goalset.R;
import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.entity.goal.Goal;

public class UpdateGoalActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {
    public static int goalId;
    public static Goal goal;
    public static Goal newGoal;
    public static EditText titleEdt;
    public static RatingBar priority;
    public static Spinner period;
    public static EditText descriptionEdt;
    public static SwitchCompat dateSwitch;
    public static Button edtBtn;
    public static Button removeBtn;
    public  static Calendar calendar;
    private static Date currDate;
    private static int newPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_goal);
        Bundle extra = getIntent().getExtras();
        goalId = extra.getInt("goalId");
        goal = DaoFactory.getDaoFactory(this).getGoalDao().findById(goalId);
        titleEdt = (EditText)findViewById(R.id.titleEdtUpd);
        titleEdt.setText(goal.getTitle());

        priority = (RatingBar)findViewById(R.id.ratingBarUpd);
        priority.setRating((float) goal.getPriority());

        period = (Spinner)findViewById(R.id.spinnerUpd);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.goalsType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        period.setAdapter(adapter);

        if(goal.getPeriod() == 1)
            period.setSelection(0);
        else
            period.setSelection(1);

        newPeriod = goal.getPeriod();
        period.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    newPeriod = 1;
                } else {
                    newPeriod = 7;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        descriptionEdt = (EditText)findViewById(R.id.editTextUpd);
        descriptionEdt.setText(goal.getDescription());

        dateSwitch = (SwitchCompat)findViewById(R.id.switch1Upd);
        edtBtn = (Button)findViewById(R.id.editBtn);
        removeBtn = (Button)findViewById(R.id.removeBtn);

        currDate = goal.getDate();
        calendar = Calendar.getInstance();
        calendar.setTime(currDate);

        dateSwitch.setOnCheckedChangeListener(this);
        edtBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.removeBtn:
                DaoFactory.getDaoFactory(this).getGoalDao().remove(goalId);
                intent = new Intent(this,GoalSetMain.class);
                startActivity(intent);
                break;
            case R.id.editBtn:
                newGoal = new Goal();
                newGoal.setId(goalId);
                newGoal.setTitle(titleEdt.getText().toString());
                newGoal.setDescription(descriptionEdt.getText().toString());
                newGoal.setDate(calendar.getTime());
                newGoal.setPeriod(newPeriod);
                newGoal.setProgress(goal.getProgress());
                newGoal.setPriority((int)priority.getRating());

                DaoFactory.getDaoFactory(this).getGoalDao().update(newGoal);
                intent = new Intent(this,GoalSetMain.class);
                startActivity(intent);
                break;
        }
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

    DatePickerDialog.OnDateSetListener myListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(year,monthOfYear,dayOfMonth);
        }
    };
}
