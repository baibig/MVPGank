package com.pierce.mvpgank.model.entity;

import java.util.List;

/**
 * Author: pierce
 * Date: 2015/9/7
 */
public class EventToImgVP {
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    List<Ganhuo> list;

    public List<Ganhuo> getList() {
        return list;
    }

    public void setList(List<Ganhuo> list) {
        this.list = list;
    }
}
