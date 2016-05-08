package lilap.com.goalset.dao.sqlLite;

import java.util.Date;
import java.util.List;

import lilap.com.goalset.dao.GoalDao;
import lilap.com.goalset.entity.goal.Goal;

/**
 * Created by Vadim on 08.05.2016.
 */
public class GoalSqlLiteDao implements GoalDao {
    private static final GoalSqlLiteDao instance = new GoalSqlLiteDao();

    public static GoalSqlLiteDao getInstance() {
        return instance;
    }

    private GoalSqlLiteDao(){}

    @Override
    public List<Goal> getGoalsCollection() {
        return null;
    }

    @Override
    public Goal findById(int id) {
        return null;
    }

    @Override
    public List<Goal> findByDate(Date date) {
        return null;
    }

    @Override
    public List<Goal> findByTitle(String title) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean removeByDate(Date date) {
        return false;
    }

    @Override
    public boolean add(Goal goal) {
        return false;
    }

    @Override
    public boolean update(Goal newGoal) {
        return false;
    }
}
