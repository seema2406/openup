package com.openup.models;

public class CreateProfileModel {
    private int types;

    public CreateProfileModel(int types) {
        this.types = types;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }
}
