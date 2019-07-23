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

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.learning.sandwich.sandy.service.GoogleSigninService;

/**
 * This class sets up the application's interactions with outside services like stetho and oauth
 */
public class SandyApplication extends Application {

  private static SandyApplication instance = null;

  /**
   * This method is used to perform nontrivial functions that will kick lazy initialization into
   * gear. It's like a digital Richard Simmons, small but super important, and hairy
   */
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    GoogleSigninService.setContext(this);
    instance = this;
    new Thread(() -> {
      SandyDatabase.getInstance(this).sandwichDao().delete();
    }).start();
  }
}
