package lilap.com.goalset.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import lilap.com.goalset.R;
import lilap.com.goalset.dao.DaoFactory;
import lilap.com.goalset.entity.goal.Goal;

public class GoalSetMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int ADD_NEW_GOAL_ACTIVITY = 1;
    public static ListView listView;
    private static final int PAGE_LEFT = 0;
    private static final int PAGE_MIDDLE = 1;
    private static final int PAGE_RIGHT = 2;
    private int mSelectedPageIndex = 1;
    public LayoutInflater layoutInflater;
    private PageModel[] mPageModel = new PageModel[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goal_set_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.mipmap.ic_add_goal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoalSetMain.this, AddGoalActivity.class);
                intent.putExtra("date",mPageModel[1].getDate());
                startActivityForResult(intent, ADD_NEW_GOAL_ACTIVITY);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initPageModel();
        layoutInflater = getLayoutInflater();
        MyPagerAdapter myPageAdapter = new MyPagerAdapter(mPageModel, this, layoutInflater);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setCurrentItem(PAGE_MIDDLE, false);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mSelectedPageIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {

                    final PageModel leftPage = mPageModel[PAGE_LEFT];
                    final PageModel middlePage = mPageModel[PAGE_MIDDLE];
                    final PageModel rightPage = mPageModel[PAGE_RIGHT];

                    final int oldLeftIndex = leftPage.getIndex();
                    final int oldMiddleIndex = middlePage.getIndex();
                    final int oldRightIndex = rightPage.getIndex();

                    if (mSelectedPageIndex == PAGE_LEFT) {

                        leftPage.setIndex(oldLeftIndex - 1);
                        middlePage.setIndex(oldLeftIndex);
                        rightPage.setIndex(oldMiddleIndex);

                        setContent(PAGE_RIGHT);
                        setContent(PAGE_MIDDLE);
                        setContent(PAGE_LEFT);

                        // user swiped to left direction --> right page
                    } else if (mSelectedPageIndex == PAGE_RIGHT) {

                        leftPage.setIndex(oldMiddleIndex);
                        middlePage.setIndex(oldRightIndex);
                        rightPage.setIndex(oldRightIndex + 1);

                        setContent(PAGE_LEFT);
                        setContent(PAGE_MIDDLE);
                        setContent(PAGE_RIGHT);
                    }
                    viewPager.setCurrentItem(PAGE_MIDDLE, false);
                }
            }
        });
    }

    private void setContent(int index) {
        final PageModel model = mPageModel[index];
        model.textView.setText(model.getText());
        List<Goal> goals = DaoFactory.getDaoFactory(this).getGoalDao().findByDate(model.getDate());
        GoalsListAdapter goalsListAdapter = new GoalsListAdapter(this,R.layout.fragment_list,goals);
        model.listView.setAdapter(goalsListAdapter);
    }

    private void initPageModel() {
        for (int i = 0; i < mPageModel.length; i++) {
            // initing the pagemodel with indexes of -1, 0 and 1
            mPageModel[i] = new PageModel(this,i - 1);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyPagerAdapter myPageAdapter = new MyPagerAdapter(mPageModel, this, layoutInflater);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setCurrentItem(PAGE_MIDDLE, false);
    }

}
