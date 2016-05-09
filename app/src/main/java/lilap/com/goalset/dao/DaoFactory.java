package lilap.com.goalset.dao;

import android.content.Context;

import lilap.com.goalset.dao.sqlLite.SqlLiteDaoFactory;

/**
 * Created by Vadim on 08.05.2016.
 */
public abstract class DaoFactory {

    public static DaoFactory getDaoFactory(Context context){
        switch ("sqlLite"){
            case "sqlLite":
                return new SqlLiteDaoFactory(context);
        }
        return null;
    }

    public abstract GoalDao getGoalDao();
}
