package com.hatbazar.domains;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/22/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Contact {
    int id=0;
    String name;
    String email;
    String phone;
    String subject;
    String message;
    boolean isNew=true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
