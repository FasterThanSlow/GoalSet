package lilap.com.goalset.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import lilap.com.goalset.R;

public class AddGoalActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener {

    Button addBtn;
    EditText titleEdt;
    DatePicker dateDp;
    Switch dateSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_goal);
        addBtn = (Button)findViewById(R.id.AddBtn);
        titleEdt = (EditText)findViewById(R.id.titleEdt);
        dateDp = (DatePicker)findViewById(R.id.datePicker);
        dateSwitch = (Switch)findViewById(R.id.switch1);

        dateSwitch.setOnCheckedChangeListener(this);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            dateDp.setVisibility(View.VISIBLE);
        }
        else{
            dateDp.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("title",titleEdt.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
