package com.example.baith21.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String title,category,Nd,date;

    private boolean ct;

    public Item() {
    }

    public Item(int id, String title, String category, String nd, String date, boolean ct) {
        this.id = id;
        this.title = title;
        this.category = category;
        Nd = nd;
        this.date = date;
        this.ct = ct;
    }

    public Item(String title, String category, String nd, String date, boolean ct) {
        this.title = title;
        this.category = category;
        Nd = nd;
        this.date = date;
        this.ct = ct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNd() {
        return Nd;
    }

    public void setNd(String nd) {
        Nd = nd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCt() {
        return ct;
    }

    public void setCt(boolean ct) {
        this.ct = ct;
    }
}
