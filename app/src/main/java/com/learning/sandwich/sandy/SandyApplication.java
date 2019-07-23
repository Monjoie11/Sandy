package com.learning.sandwich.sandy;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * This class sets up the application's interactions with outside services like stetho and oauth
 */
public class SandyApplication extends Application {

  private static SandyApplication instance = null;

  /**
   * This method is used to perform nontrivial functions that will kick lazy initialization into
   * gear. It's like a digital Richard Simmons, small but super important
   */
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
