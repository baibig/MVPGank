package com.pierce.mvpgank.model.entity;

import java.util.List;

/**
 * Author: pierce
 * Date: 2015/9/6
 */
public class GankByDay {
    boolean error;
    String[] category;
    Holder results;

    public boolean isError() {
        return error;
    }

    public String[] getCategory() {
        return category;
    }

    public Holder getResults() {
        return results;
    }
}
class Holder{
    List<Ganhuo> IOS;
    List<Ganhuo> Android;
}
