package com.learning.sandwich.sandy;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
//import com.facebook.AccessToken;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

// CallbackManager callbackManager = CallbackManager.Factory.create();

  private TextView mTextMessage;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          mTextMessage.setText(R.string.title_home);
          return true;
        case R.id.navigation_dashboard:
          mTextMessage.setText(R.string.title_dashboard);
          return true;
        case R.id.navigation_notifications:
          mTextMessage.setText(R.string.title_notifications);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


//    AccessToken accessToken = AccessToken.getCurrentAccessToken();
//    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



//    callbackManager = CallbackManager.Factory.create();
//
//    LoginManager.getInstance().registerCallback(callbackManager,
//        new FacebookCallback<LoginResult>() {
//
//
//          @Override
//          public void onSuccess(LoginResult loginResult) {
//            // App code
//          }
//
//          @Override
//          public void onCancel() {
//            // App code
//          }
//
//          @Override
//          public void onError(FacebookException exception) {
//            // App code
//          }
//
//        });
//
//
//  }
//
//  @Override
//  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//   callbackManager.onActivityResult(requestCode, resultCode, data);
//    super.onActivityResult(requestCode, resultCode, data);
  }


}
