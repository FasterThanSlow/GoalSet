package lilap.com.goalset.controller.GoalsList;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lilap.com.goalset.R;
import lilap.com.goalset.controller.GoalSetMain;
import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.entity.goal.Goal;

/**
 * Created by Vadim on 10.05.2016.
 */
public class GoalsListAdapter extends ArrayAdapter<Goal> {
    Context context;

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
            SwitchCompat progress = (SwitchCompat)view.findViewById(R.id.achived);


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

            progress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                progress.setChecked(false);
            }else {
                progress.setChecked(true);
            }

        }
        return view;
    }
}
