package com.pierce.mvpgank.ui.view;

import com.pierce.mvpgank.model.entity.Gank;

/**
 * Author: pierce
 * Date: 2015/9/6
 */
public interface MainView {
    void error();
    void setFresh(Gank gank);
    void setLoadMore(Gank gank);
}
