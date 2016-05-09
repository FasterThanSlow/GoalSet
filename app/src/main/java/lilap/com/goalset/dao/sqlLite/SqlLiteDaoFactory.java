package lilap.com.goalset.dao.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.dao.GoalDao;

/**
 * Created by Vadim on 08.05.2016.
 */
public class SqlLiteDaoFactory extends DaoFactory {
    private Context context;
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public SqlLiteDaoFactory(Context context){
        this.context = context;
        this.dbHelper = new DBHelper(context);
        this.database = dbHelper.getWritableDatabase();
    }

    @Override
    public GoalDao getGoalDao() {
        return new GoalSqlLiteDao(this.database);
    }
}
