package lilap.com.goalset.dao.sqlLite;

import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.dao.GoalDao;

/**
 * Created by Vadim on 08.05.2016.
 */
public class SqlLiteDaoFactory extends DaoFactory {

    private final static SqlLiteDaoFactory instance = new SqlLiteDaoFactory();

    public static SqlLiteDaoFactory getInstance(){
        return instance;
    }

    private SqlLiteDaoFactory(){}

    @Override
    public GoalDao getGoalDao() {
        return GoalSqlLiteDao.getInstance();
    }
}
