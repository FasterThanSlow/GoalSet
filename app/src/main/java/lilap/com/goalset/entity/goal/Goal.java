package lilap.com.goalset.entity.goal;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Vadim on 08.05.2016.
 */
public class Goal {
    private int id;
    private String title;
    private String description;
    private int progress;
    private Date date;
    private int period;
    private int priority;

    public Goal(int id, String title, String description, int progress, Date date, int period, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.progress = progress;
        this.date = date;
        this.period = period;
        this.priority = priority;
    }

    public Goal(){
    }

    public int getPriority() { return priority;}

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
