package com.pierce.mvpgank.ui.activity;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.pierce.mvpgank.R;
import com.pierce.mvpgank.model.entity.EventToImgVP;
import com.pierce.mvpgank.model.entity.Ganhuo;
import com.pierce.mvpgank.model.entity.Gank;
import com.pierce.mvpgank.ui.fragment.ImageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class ImageActivity extends BaseActivity {

    private final String TAG=ImageActivity.class.getSimpleName();

    @Bind(R.id.toolbar_img)
    Toolbar toolbar;
    @Bind(R.id.viewpager_img)
    ViewPager viewPager;
    MyAdapter adapter;
    int position;
    boolean isShow=true;

    List<Ganhuo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        data=EventBus.getDefault().getStickyEvent(EventToImgVP.class).getList();
        position=EventBus.getDefault().getStickyEvent(EventToImgVP.class).getPosition();
        adapter=new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

    private class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle=new Bundle();
            Ganhuo ganhuo=data.get(position);
            bundle.putString("meizhi", ganhuo.getUrl());
            ImageFragment fragment=new ImageFragment();
            fragment.setOnTouchListener(new ImageFragment.OnTouchListener() {
                @Override
                public void onTouch() {
                    toggleToolBar();
                }
            });
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }

    private void toggleToolBar(){
        if (isShow) {
            getSupportActionBar().hide();
            isShow=false;
        }
        else {
            getSupportActionBar().show();
            isShow=true;
        }
    }

}
