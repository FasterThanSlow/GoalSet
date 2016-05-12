package lilap.com.goalset.controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lilap.com.goalset.R;
import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.entity.goal.Goal;

/**
 * Created by Vadim on 09.03.2016.
 */
public class PageModel {

    private int index;
    private String text;
    public Date date;
    private Context context;
    public ListView listView;
    public TextView textView;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public PageModel(Context context,int index) {
        this.index = index;
        this.context = context;
        setIndex(index);
    }

    public int getIndex() {
        return index;
    }


    public void setIndex(int index) {
        this.index = index;
        setText(index);
    }


    public String getText() {
        return text;
    }

    public Date getDate(){return this.date;}

    private void setText(int index) {
        this.date = new Date(System.currentTimeMillis());
        this.date.setTime(this.date.getTime() + 86400000*index);
        this.text = String.format("Цели на %s", simpleDateFormat.format(this.date));
    }
}