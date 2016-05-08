package lilap.com.goalset.dao;

import java.util.Date;
import java.util.List;

import lilap.com.goalset.entity.goal.Goal;

/**
 * Created by Vadim on 08.05.2016.
 */
public interface GoalDao {
    List<Goal> getGoalsCollection();
    Goal findById(int id);
    List<Goal> findByDate(Date date);
    List<Goal> findByTitle(String title);

    boolean remove(int id);
    boolean removeByDate(Date date);

    boolean add(Goal goal);
    boolean update(Goal newGoal);
}
