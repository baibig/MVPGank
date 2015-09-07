package com.pierce.mvpgank.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: pierce
 * Date: 2015/9/6
 */
public class Gank {
    boolean error;
    List<Ganhuo> results;
    int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<Ganhuo> results) {
        this.results = results;
    }

    public List getResults() {
        return results;
    }
}

