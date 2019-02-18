package com.cicinnus.cateye.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.cicinnus.cateye.R;
import com.cicinnus.cateye.module.cinema.CinemaFragment;
import com.cicinnus.cateye.module.discover.DiscoverFragment;
import com.cicinnus.cateye.module.mine.MineMVPFragment;
import com.cicinnus.cateye.module.movie.movie_main.MovieMainFragment;
import com.cicinnus.cateye.tools.ToastUtil;
import com.cicinnus.retrofitlib.base.BaseMVPActivity;
import com.cicinnus.retrofitlib.utils.CleanLeakUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/18.
 */
public class MainActivity extends BaseMVPActivity {

    @BindView(R.id.rg_main)
    RadioGroup rg_main;

    private MovieMainFragment movieMainFragment;
    private CinemaFragment cinemaFragment;
    private DiscoverFragment discoverFragment;
    private MineMVPFragment mineFragment;
    private long firstTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        setupRg();
    }

    @Override
    protected void initEventAndData(Bundle savedStated) {
        if (savedStated != null) {
            movieMainFragment = (MovieMainFragment) getSupportFragmentManager().findFragmentByTag("movieMainFragment");
            cinemaFragment = (CinemaFragment) getSupportFragmentManager().findFragmentByTag("cinemaFragment");
            discoverFragment = (DiscoverFragment) getSupportFragmentManager().findFragmentByTag("discoverFragment");
            mineFragment = (MineMVPFragment) getSupportFragmentManager().findFragmentByTag("mineFragment");
            switchFragment(BaseConstant.RB_MOVIE);
        } else {
            movieMainFragment = MovieMainFragment.newInstance();
            cinemaFragment = CinemaFragment.newInstance();
            discoverFragment = DiscoverFragment.newInstance();
            mineFragment = MineMVPFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fl_container_main, movieMainFragment, "movieMainFragment")
                    .add(R.id.fl_container_main, cinemaFragment, "cinemaFragment")
                    .add(R.id.fl_container_main, discoverFragment, "discoverFragment")
                    .add(R.id.fl_container_main, mineFragment, "mineFragment")
                    .commit();
            switchFragment(BaseConstant.RB_MOVIE);
        }
    }

    /**
     * RadioGroup点击监听
     */
    private void setupRg() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_movie:
                        switchFragment(BaseConstant.RB_MOVIE);
                        break;
                    case R.id.rb_cinema:
                        switchFragment(BaseConstant.RB_CINEMA);
                        break;
                    case R.id.rb_discover:
                        switchFragment(BaseConstant.RB_DISCOVER);
                        break;
                    case R.id.rb_mine:
                        switchFragment(BaseConstant.RB_MINE);
                        break;
                }
            }
        });
    }

    /**
     * 切换Fragment
     *
     * @param index 下标
     */
    private void switchFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case BaseConstant.RB_MOVIE:
                transaction
                        .show(movieMainFragment)
                        .hide(cinemaFragment)
                        .hide(discoverFragment)
                        .hide(mineFragment);
                movieMainFragment.setUserVisibleHint(true);
                cinemaFragment.setUserVisibleHint(false);
                discoverFragment.setUserVisibleHint(false);
                mineFragment.setUserVisibleHint(false);
                break;
            case BaseConstant.RB_CINEMA:
                transaction
                        .show(cinemaFragment)
                        .hide(movieMainFragment)
                        .hide(discoverFragment)
                        .hide(mineFragment);
                cinemaFragment.setUserVisibleHint(true);
                movieMainFragment.setUserVisibleHint(false);
                discoverFragment.setUserVisibleHint(false);
                mineFragment.setUserVisibleHint(false);
                break;
            case BaseConstant.RB_DISCOVER:
                transaction
                        .show(discoverFragment)
                        .hide(cinemaFragment)
                        .hide(movieMainFragment)
                        .hide(mineFragment);
                discoverFragment.setUserVisibleHint(true);
                cinemaFragment.setUserVisibleHint(false);
                movieMainFragment.setUserVisibleHint(false);
                mineFragment.setUserVisibleHint(false);
                break;
            case BaseConstant.RB_MINE:
                transaction
                        .show(mineFragment)
                        .hide(cinemaFragment)
                        .hide(discoverFragment)
                        .hide(movieMainFragment);
                mineFragment.setUserVisibleHint(true);
                cinemaFragment.setUserVisibleHint(false);
                discoverFragment.setUserVisibleHint(false);
                movieMainFragment.setUserVisibleHint(false);
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 32 && resultCode == 33) {
            movieMainFragment.onActivityResult(requestCode, resultCode, data);
            cinemaFragment.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 56 && resultCode == 33) {
            movieMainFragment.onActivityResult(requestCode, resultCode, data);
            cinemaFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            firstTime = secondTime;
            ToastUtil.showToast("再次点击返回退出应用");
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        CleanLeakUtils.fixInputMethodManagerLeak(MainActivity.this);
        super.onDestroy();
    }
}
