package com.openup.data.network;



import com.openup.data.network.model.LoginRequest;
import com.openup.data.network.model.LoginResponse;
import com.openup.data.network.model.LogoutResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Niel on 24/07/17.
 */

public interface RetrofitApiService {

        @POST("/data")
        Call<LoginResponse> doGoogleLoginApiCall(@Body LoginRequest.GoogleLoginRequest request);

        @POST("/data")
        Call<LoginResponse> doFacebookLoginApiCall(@Body LoginRequest.FacebookLoginRequest request);

        @POST("/data")
        Call<LoginResponse> doLoginApiCall(@Body LoginRequest.ServerLoginRequest request);

        @POST("/doLogout")
        Call<LogoutResponse> doLogoutApiCall();


}