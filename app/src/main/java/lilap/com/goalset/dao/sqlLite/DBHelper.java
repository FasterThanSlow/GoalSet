package lilap.com.goalset.dao.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vadim on 09.05.2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GoalSet";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + GoalSqlLiteDao.TABLE_NAME + "(" +
            GoalSqlLiteDao.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GoalSqlLiteDao.COLUMN_TITLE + " TEXT NOT NULL, " +
            GoalSqlLiteDao.COLUMN_DESCRIPTION + " TEXT, " +
            GoalSqlLiteDao.COLUMN_PRIORITY + " INTEGER NOT NULL, " +
            GoalSqlLiteDao.COLUMN_DATE + " INTEGER NOT NULL, " +
            GoalSqlLiteDao.COLUMN_PERIOD + " INTEGER NOT NULL, " +
            GoalSqlLiteDao.COLUMN_PROGRESS + " INTEGER NOT NULL);"
            );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GoalSqlLiteDao.TABLE_NAME);
        onCreate(db);
    }
}
