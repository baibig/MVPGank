package com.pierce.mvpgank.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.pierce.mvpgank.R;
import com.pierce.mvpgank.adapter.MainRecyclerViewAdapter;
import com.pierce.mvpgank.model.entity.EventToImgVP;
import com.pierce.mvpgank.model.entity.Ganhuo;
import com.pierce.mvpgank.model.entity.Gank;
import com.pierce.mvpgank.presenter.MainPresenter;
import com.pierce.mvpgank.presenter.impl.MainPresenterImpl;
import com.pierce.mvpgank.ui.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity implements MainView,SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG=MainActivity.class.getSimpleName();

    private final int REFRESH=0;
    private final int  LOADMORE=1;

    @Bind(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recyclerview) RecyclerView recyclerView;
    @Bind(R.id.fab_refresh) FloatingActionButton fab;
    @Bind(R.id.toolbar) Toolbar toolbar;

    MainPresenter mainPresenter;
    MainRecyclerViewAdapter adapter;
    int currentPage=1;
    List<Ganhuo> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mainPresenter=new MainPresenterImpl(this);
        mainPresenter.getGank(REFRESH, currentPage);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        final StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        adapter=new MainRecyclerViewAdapter(this,data);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleCount = manager.getChildCount();
                int totalCount = manager.getItemCount();
                int pastCount = manager.findFirstVisibleItemPositions(new int[2])[0];
                if (!refreshLayout.isRefreshing()) {
                    if (visibleCount + pastCount >= totalCount) {
                        loadMore();
                    }
                }
            }
        });
        adapter.setOnClickViewListener(new MainRecyclerViewAdapter.OnClickViewListener() {
            @Override
            public void onClick(View v, ImageView imageView, View card, int position) {
                if (v==null) return;
                if (v==imageView){
                    Log.i(TAG, v.toString());
                    EventToImgVP event=new EventToImgVP();
                    event.setList(data);
                    event.setPosition(position);
                    EventBus.getDefault().postSticky(event);
                    startActivity(new Intent(MainActivity.this, ImageActivity.class));
                }
                else if (v==card){
                    Log.i(TAG,v.toString());
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshLayout.setRefreshing(true);
                mainPresenter.getGank(REFRESH,1);
            }
        });
    }

    @Override
    public void error() {

    }

    @Override
    public void setFresh(Gank gank) {
        data.addAll(gank.getResults());
        adapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setLoadMore(Gank gank) {
        data.addAll(data.size(),gank.getResults());
        adapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }

    public void loadMore() {
        currentPage++;
        mainPresenter.getGank(LOADMORE,currentPage);

    }

    @Override
    public void onRefresh() {
        mainPresenter.getGank(REFRESH,currentPage);
    }
}
