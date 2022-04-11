package com.jhn.carmpgpriceproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jhn.carmpgpriceproject.api.ImageApi;
import com.jhn.carmpgpriceproject.api.NetworkClient;
import com.jhn.carmpgpriceproject.api.UserApi;
import com.jhn.carmpgpriceproject.model.UserInfoRes;
import com.jhn.carmpgpriceproject.model.UserRes;
import com.jhn.carmpgpriceproject.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

// 응답 처리를 위한 RequestQueue 를 사용하기 위한 클래스 ... : volley 라이브러리를 활용
public class MainActivity extends AppCompatActivity {
    // activity_main.xml 의 FrameLayout
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    ProgressDialog progressDialog;
    String accessToken;
    String User_email;
    String User_nickname;

    // activity_main.xml 의 라이브러리를 이용한 위젯
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        // 기본 플래그먼트 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        // 메인화면 하단의 탭들을 구성하는 네비게이션
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 하단의 탭을 선택했을 때, 이벤트를 처리
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();

                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();

                        return true;
                    case R.id.tab4:
                        SharedPreferences sp = getSharedPreferences(Utils.PREFERENCES_NAME, MODE_PRIVATE);
                        accessToken = sp.getString("accessToken", "");
                        if(accessToken.isEmpty()){
                            // 로그인 액티비티를 띄운다.
                            Toast.makeText(MainActivity.this,
                                    "죄송합니다 현재 이용 불가 합니다 ㅠ",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            showProgress("내 정보 가져오는 중 입니다....");

                            Retrofit retrofit = NetworkClient.getRetrofitClient(MainActivity.this);
                            UserApi api = retrofit.create(UserApi.class);
                            Call<UserInfoRes> call = api.UserInfo("Bearer "+ accessToken);

                            call.enqueue(new Callback<UserInfoRes>() {
                            public void onResponse(Call<UserInfoRes> call, Response<UserInfoRes> response) {
                            dismissProgress();
                                if(response.isSuccessful()){
                                    // 200 OK 인 경우
                                    // 정상 저장되었으면, 이 액티비티는 끝낸다.
                                    User_email = response.body().getUser_email();
                                    User_nickname =response.body().getUser_nickname();
                                    Intent intent = new Intent(MainActivity.this, Fragment4.class);
                                    intent.putExtra("User_email",User_email);
                                    intent.putExtra("User_nickname",User_nickname);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(MainActivity.this,
                                            "죄송합니다 현재 이용 불가 합니다 ㅠㅠ",
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }

                            @Override
                            public void onFailure(Call<UserInfoRes> call, Throwable t) {
                                dismissProgress();
                                Toast.makeText(MainActivity.this,
                                        "죄송합니다 현재 이용 불가 합니다 ㅠ",
                                        Toast.LENGTH_SHORT).show();
                                return;

                                }
                            });

                            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();
                        }
                        return true;
                }
                return false;
            }
        });
    }

    private void showProgress(String message){
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        progressDialog.show();
    }
    private void dismissProgress(){
        progressDialog.dismiss();
    }

}










