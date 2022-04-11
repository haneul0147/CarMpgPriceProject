package com.jhn.carmpgpriceproject.api;

import com.jhn.carmpgpriceproject.model.UserInfoRes;
import com.jhn.carmpgpriceproject.model.UserReq;
import com.jhn.carmpgpriceproject.model.UserRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserApi {
    // 로그인 API
    @POST("/dev/api/v1/user/login")
    Call<UserRes> userLogin(@Body UserReq userReq);

    // 로그아웃 API
    @POST("/dev/api/v1/user/logout")
    Call<UserRes> userLogout(@Header("Authorization") String accessToken);

    // 회원가입 API
    @POST("/dev/api/v1/user/register")
    Call<UserRes> userSignUp(@Body UserReq userReq);

    // 내정보 가져오기
    @GET("/dev/api/v1/user/me")
    Call<UserInfoRes> UserInfo(@Header("Authorization") String accessToken);

}
