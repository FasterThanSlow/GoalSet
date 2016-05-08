package lilap.com.goalset.dao;

import lilap.com.goalset.dao.sqlLite.SqlLiteDaoFactory;

/**
 * Created by Vadim on 08.05.2016.
 */
public abstract class DaoFactory {

    public static DaoFactory getDaoFactory(){
        switch ("sqlLite"){
            case "sqlLite":
                return SqlLiteDaoFactory.getInstance();
        }
        return null;
    }

    public abstract GoalDao getGoalDao();
}
