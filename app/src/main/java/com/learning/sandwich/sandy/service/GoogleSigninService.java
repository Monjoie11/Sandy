package com.learning.sandwich.sandy.service;

import android.app.Application;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleSigninService {



  private static Application context;
  private GoogleSignInAccount account;
  private GoogleSignInClient client;



  private GoogleSigninService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail().requestId().build();
    client = GoogleSignIn.getClient(context, options);
  }

  public static GoogleSigninService getInstance(){
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder{
    private static final GoogleSigninService INSTANCE = new GoogleSigninService();
  }

  public static void setContext(Application context) {
    GoogleSigninService.context = context;
  }

  public GoogleSignInAccount getAccount() {
    return account;
  }

  public void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }

  public GoogleSignInClient getClient() {
    return client;
  }



}
