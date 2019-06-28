package com.learning.sandwich.sandy;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class SandyApplication extends Application {

  private static SandyApplication instance = null;

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    instance = this;
    new Thread(() -> {
      SandyDatabase.getInstance(this).sandwichDao().delete();
    }).start();
  }
}
