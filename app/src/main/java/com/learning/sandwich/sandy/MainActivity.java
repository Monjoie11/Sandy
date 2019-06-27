package com.learning.sandwich.sandy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
//import com.facebook.AccessToken;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
import androidx.navigation.Navigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.preference.PreferenceManager;


public class MainActivity extends AppCompatActivity {





  private TextView mTextMessage;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(  savedInstanceState);
    setContentView(R.layout.activity_main);
    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putBoolean(getString(R.string.saved_tutorial_complete_key), false);
    editor.apply();
    //upon start check to see if sandy's been educated. if yes, inflate sandwichImage (capture) fragment, if not inflate response frag to tutorial
    if (!sharedPref.getBoolean(getString(R.string.saved_tutorial_complete_key), false) == false) {
      Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_responseFragment_to_sandwichImageFragment);
    }
    //setContentView(R.layout.sandwich_fragment);

  }
}