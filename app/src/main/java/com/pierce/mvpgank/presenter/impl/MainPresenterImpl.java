package com.pierce.mvpgank.presenter.impl;

import android.util.Log;

import com.pierce.mvpgank.model.MainModel;
import com.pierce.mvpgank.model.entity.*;
import com.pierce.mvpgank.model.entity.Error;
import com.pierce.mvpgank.model.impl.MainModelImpl;
import com.pierce.mvpgank.presenter.MainPresenter;
import com.pierce.mvpgank.ui.view.MainView;

import de.greenrobot.event.EventBus;

/**
 * Author: pierce
 * Date: 2015/9/6
 */
public class MainPresenterImpl implements MainPresenter {
    private final static String TAG=MainPresenterImpl.class.getSimpleName();
    private MainModel mainModel;
    private MainView mainView;
    public MainPresenterImpl(MainView view){
        mainView=view;
        mainModel=new MainModelImpl();
        EventBus.getDefault().registerSticky(this);
    }
    @Override
    public void getGank(int state, int currentPage) {
        mainModel.loadGank(state, currentPage);
    }
    public void onEvent(Gank gank){
        if (gank.getState()==1)
            mainView.setLoadMore(gank);
        else
            mainView.setFresh(gank);
    }
    public void onEvent(Error error){
        mainView.error();
        Log.i(TAG,error.getMessage());
    }
}
