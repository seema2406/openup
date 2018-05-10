package com.openup.data.db;


import com.openup.models.UserModel;

public interface IDbHelper {

    Long insertUser(final UserModel user);

    UserModel getUserModel();

    String getUserId();
}