package lilap.com.goalset.dao.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lilap.com.goalset.dao.GoalDao;
import lilap.com.goalset.entity.goal.Goal;

/**
 * Created by Vadim on 08.05.2016.
 */
public class GoalSqlLiteDao implements GoalDao {
    public static final String TABLE_NAME = "goals";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PERIOD = "period";
    public static final String COLUMN_PROGRESS = "progress";
    private SQLiteDatabase database;


    private static final String[] COLUMNS = new String[]{
        COLUMN_ID,
        COLUMN_TITLE,
        COLUMN_DESCRIPTION,
        COLUMN_PROGRESS,
        COLUMN_DATE,
        COLUMN_PERIOD,
        COLUMN_PRIORITY,
        COLUMN_PROGRESS
    };


    private ContentValues getValues(Goal goal){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,goal.getTitle());
        values.put(COLUMN_DESCRIPTION,goal.getDescription());
        values.put(COLUMN_PRIORITY,goal.getPriority());
        values.put(COLUMN_DATE,goal.getDate().getTime());
        values.put(COLUMN_PERIOD,goal.getPeriod());
        values.put(COLUMN_PROGRESS,goal.getProgress());
        return values;
    }

    public GoalSqlLiteDao(SQLiteDatabase database){
        this.database = database;
    }

    @Override
    public List<Goal> getGoalsCollection() {
        Cursor cursor = database.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
        return getListGoals(cursor);
    }

    @Override
    public Goal findById(int id) {
        Cursor cursor = database.query(TABLE_NAME, COLUMNS, COLUMN_ID + "=" + id, null, null, null, null);
        if (cursor.moveToFirst()) {
            return CursorToGoal(cursor);
        }
        else{
            return null;
        }
    }

    @Override
    public List<Goal> findByDate(Date date) {
        Cursor cursor = database.query(TABLE_NAME, COLUMNS, COLUMN_DATE + "=" + date.getTime(), null, null, null, null);
        return getListGoals(cursor);
    }

    @Override
    public List<Goal> findByTitle(String title) {
        Cursor cursor = database.query(TABLE_NAME, COLUMNS, COLUMN_TITLE + "=" + title, null, null, null, null);
        return getListGoals(cursor);
    }

    @Override
    public boolean remove(int id) {
        return database.delete(TABLE_NAME, COLUMN_ID + "=" + id, null) > 0;
    }

    @Override
    public boolean removeByDate(Date date) {
        return database.delete(TABLE_NAME, COLUMN_DATE + "=" + date.getTime(), null) > 0;
    }

    @Override
    public boolean add(Goal goal) {
        return database.insert(TABLE_NAME,null,getValues(goal)) > 0;
    }

    @Override
    public boolean update(Goal newGoal) {
        return database.update(TABLE_NAME, getValues(newGoal), COLUMN_ID + "=" + newGoal.getId(), null) > 0;
    }

    private List<Goal> getListGoals(Cursor cursor){
        List<Goal> goals = new ArrayList<Goal>();
        if (cursor.moveToFirst()) {
            do {
                goals.add(CursorToGoal(cursor));
            } while (cursor.moveToNext());
        }
        return goals;
    }

    private Goal CursorToGoal(Cursor cursor){
        Goal goal = new Goal();
        goal.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        goal.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        goal.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
        goal.setPriority(cursor.getInt(cursor.getColumnIndex(COLUMN_PRIORITY)));
        goal.setDate(new Date(cursor.getLong(cursor.getColumnIndex(COLUMN_DATE))));
        goal.setPeriod(cursor.getInt(cursor.getColumnIndex(COLUMN_PERIOD)));
        goal.setProgress(cursor.getInt(cursor.getColumnIndex(COLUMN_PROGRESS)));
        return goal;
    }
}
