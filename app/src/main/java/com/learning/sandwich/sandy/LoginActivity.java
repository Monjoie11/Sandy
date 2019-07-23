package com.learning.sandwich.sandy;

import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.learning.sandwich.sandy.service.GoogleSigninService;

public class LoginActivity extends AppCompatActivity {

  private static final int LOGIN_RESQUEST_CODE = 1000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    findViewById(R.id.sign_in).setOnClickListener( (view ) -> signIn() );
  }

  @Override
  protected void onStart() {
    super.onStart();
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if(account != null){
      GoogleSigninService.getInstance().setAccount(account);
      switchToMain();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
   if (requestCode == LOGIN_RESQUEST_CODE){
     try {
       Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
       GoogleSignInAccount account = task.getResult(ApiException.class);
       GoogleSigninService.getInstance().setAccount(account);
       switchToMain();
     } catch (ApiException e) {
       Toast.makeText(this, getString(R.string.auth_fail), Toast.LENGTH_LONG).show();
     }
   }
  }

  private void signIn(){
    Intent intent = GoogleSigninService.getInstance().getClient().getSignInIntent();
    startActivityForResult(intent, LOGIN_RESQUEST_CODE);
  }

  private void switchToMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

}
