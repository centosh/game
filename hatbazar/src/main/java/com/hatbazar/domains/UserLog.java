package com.hatbazar.domains;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/29/13
 * Time: 12:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserLog {
    int id;
    String userName;
    String name;
    int userId;
    String action;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
