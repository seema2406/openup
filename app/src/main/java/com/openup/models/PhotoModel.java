package com.openup.models;

public class PhotoModel {
    private PhotoModel photoModel;
    private int type;

    public PhotoModel(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
