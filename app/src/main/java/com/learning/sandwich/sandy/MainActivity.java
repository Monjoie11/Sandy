package com.learning.sandwich.sandy;

/*
    Copyright 2019 Jeff Franken
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

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


  /**
   * @param savedInstanceState The main activity doesn't do much but either host the response
   * fragment or, if the tutorial is complete, steer navigation to SandwichImage fragment. Silly
   * main activity, Jetpack made you obsolete
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SharedPreferences sharedPref = PreferenceManager
        .getDefaultSharedPreferences(this); //getPreferences(Context.MODE_PRIVATE);

    //upon start check to see if sandy's been educated. if yes, inflate sandwichImage (capture) fragment, if not inflate response frag to tutorial
    if (sharedPref.getBoolean(getString(R.string.saved_tutorial_complete_key), false)) {
      Navigation.findNavController(this, R.id.nav_host_fragment)
          .navigate(R.id.action_responseFragment_to_sandwichImageFragment);
    }
  }

}