package com.hatbazar.domains;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/21/13
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemSold {
    int id;
    int item;
    Date date;
    int requestBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(int requestBy) {
        this.requestBy = requestBy;
    }
}
