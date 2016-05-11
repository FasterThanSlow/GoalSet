package lilap.com.goalset.controller;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lilap.com.goalset.R;
import lilap.com.goalset.controller.GoalsList.GoalsListAdapter;
import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.entity.goal.Goal;

/**
 * Created by Vadim on 11.05.2016.
 */
public class MyPageAdapter extends PagerAdapter {
    List<View> pages = new ArrayList<View>();
    Date date;

    public MyPageAdapter(Context context,Date date){
        LayoutInflater inflater = LayoutInflater.from(context);
        View page;
        ListView listView;
        TextView textView;
        List<Goal> goals = DaoFactory.getDaoFactory(context).getGoalDao().getGoalsCollection();
        this.date = date;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        for (int i = 0; i < 3; i++) {
            page = inflater.inflate(R.layout.fragment_list, null);
            listView = (ListView) page.findViewById(R.id.goalsListView);
            textView = (TextView) page.findViewById(R.id.textView4);
            this.date = new Date(date.getTime() - 86400000 + 86400000*i);
            textView.setText("Цели на " + simpleDateFormat.format(this.date).toString());
            GoalsListAdapter goalsListAdapter = new GoalsListAdapter(context, R.layout.goal_list_item, goals);
            listView.setAdapter(goalsListAdapter);
            pages.add(page);
        }

        date.setTime(1);
    }

    @Override
    public Object instantiateItem(View collection, int position){
        View v = pages.get(position);
        ((ViewPager) collection).addView(v, 0);
        return v;
    }

    @Override
    public void destroyItem(View collection, int position, Object view){
        ((ViewPager) collection).removeView((View) view);
    }

    @Override
    public int getCount(){
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view.equals(object);
    }


    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1){
    }

    @Override
    public Parcelable saveState(){
        return null;
    }

    @Override
    public void startUpdate(View arg0){
    }
}
