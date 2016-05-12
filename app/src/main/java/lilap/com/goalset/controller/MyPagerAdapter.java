package lilap.com.goalset.controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import lilap.com.goalset.R;
import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.entity.goal.Goal;

/**
 * Created by Vadim on 11.05.2016.
 */
public class MyPagerAdapter extends PagerAdapter {
    private PageModel[] pageModels;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyPagerAdapter(PageModel[] pageModels, Context context, LayoutInflater layoutInflater) {
        this.pageModels = pageModels;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // we only need three pages
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View fragmentLayout = layoutInflater.inflate(R.layout.fragment_list, container, false);
        TextView textView = (TextView)fragmentLayout.findViewById(R.id.textView4);
        ListView listView = (ListView)fragmentLayout.findViewById(R.id.goalsListView);
        PageModel currentPage = pageModels[position];
        List<Goal> goals = DaoFactory.getDaoFactory(context).getGoalDao().findByDate(currentPage.getDate());
        GoalsListAdapter goalsListAdapter = new GoalsListAdapter(this.context,R.layout.goal_list_item,goals);
        listView.setAdapter(goalsListAdapter);
        currentPage.textView = textView;
        currentPage.listView = listView;
        currentPage.listView.setAdapter(goalsListAdapter);
        textView.setText(currentPage.getText());
        container.addView(fragmentLayout);
        return fragmentLayout;
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
