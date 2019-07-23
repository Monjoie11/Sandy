package com.learning.sandwich.sandy.service;

import android.app.Application;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/**
 * This public class contains the methods calledfor GoogleSignIn by the LoginActivity. It is a singleton, and only oe instance of the object make exist at a time
 */
public class GoogleSigninService {



  private static Application context;
  private GoogleSignInAccount account;
  private GoogleSignInClient client;



  private GoogleSigninService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail().requestId().build();
    client = GoogleSignIn.getClient(context, options);
  }

  /**This method returns THE instance of the class
   * @return
   */
  public static GoogleSigninService getInstance(){
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder{
    private static final GoogleSigninService INSTANCE = new GoogleSigninService();
  }

  /** This method set's the context within which the GoogleSignIn Service will be evoked and is necessary for all subsequent methods requiring a context reference as a parameter
   * @param context
   */
  public static void setContext(Application context) {
    GoogleSigninService.context = context;
  }

  /**Getter for private fild Sccoun
   * @return
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /**Setter for private field account
   * @param account
   */
  public void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }

  /**Getter for google signIn Client
   * @return
   */
  public GoogleSignInClient getClient() {
    return client;
  }



}
