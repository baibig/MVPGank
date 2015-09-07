package com.pierce.mvpgank.model.entity;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Author: pierce
 * Date: 2015/9/6
 */
public class Ganhuo extends RealmObject{
    @PrimaryKey
    private String objectId;

    private String who;
    private String desc;
    private String type;
    private String url;
    private boolean used;

    private Date publishedAt;
    private Date createdAt;
    private Date updatedAt;

    public String getObjectId() {
        return objectId;
    }

    public String getWho() {
        return who;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isUsed() {
        return used;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
