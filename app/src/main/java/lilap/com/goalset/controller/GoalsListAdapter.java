package lilap.com.goalset.controller;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import lilap.com.goalset.R;
import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.entity.goal.Goal;


/**
 * Created by Vadim on 10.05.2016.
 */
public class GoalsListAdapter extends ArrayAdapter<Goal> {
    Context context;
    public static final int UPDATE_ACTIVITY = 3;

    public GoalsListAdapter(Context context, int resource, List<Goal> goals) {
        super(context, resource, goals);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.goal_list_item, null);
        }

        final Goal goal = getItem(position);

        if(goal != null){
            TextView goalTitle = (TextView)view.findViewById(R.id.titleTV);
            ImageView priorityImg = (ImageView)view.findViewById(R.id.priority);
            CheckBox checkBox = (CheckBox)view.findViewById(R.id.checkBox);

            goalTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UpdateGoalActivity.class);
                    intent.putExtra("goalId", goal.getId());
                    context.startActivity(intent);
                }
            });
            goalTitle.setText(goal.getTitle());

            switch (goal.getPriority()) {
                case 3:
                    priorityImg.setImageResource(R.mipmap.ic_hight);
                    break;
                case 2:
                    priorityImg.setImageResource(R.mipmap.ic_medium);
                    break;
                case 1:
                    priorityImg.setImageResource(R.mipmap.ic_low);
                    break;
            }

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        goal.setProgress(100);
                        DaoFactory.getDaoFactory(context).getGoalDao().update(goal);
                    }
                    else{
                        goal.setProgress(0);
                        DaoFactory.getDaoFactory(context).getGoalDao().update(goal);
                    }
                }
            });

            if(goal.getProgress() == 0) {
                checkBox.setChecked(false);
            }else {
                checkBox.setChecked(true);
            }

        }
        return view;
    }
}
