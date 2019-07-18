package com.learning.sandwich.sandy;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.learning.sandwich.sandy.model.Response;
import com.learning.sandwich.sandy.model.Sandwich;
import java.util.List;

public class ResponseViewModel extends AndroidViewModel {
  private LiveData<List<Response>> response;
  private LiveData<List<Sandwich>> sandwich;

  public ResponseViewModel(@NonNull Application application) {
    super(application);
    SandyDatabase db = SandyDatabase.getInstance(application);
    sandwich = db.sandwichDao().getAll();
    response = db.responseDao().getAll();
  }

  public LiveData<List<Sandwich>> getSandwichLiveData() {
    return sandwich;
  }

  public LiveData<List<Response>> getResponseLiveData() {
    return response;
  }

  public void addSanwich(final Sandwich sandwich){
    new Thread(new Runnable() {
      @Override
      public void run() {
        SandyDatabase db = SandyDatabase.getInstance(getApplication());
        db.sandwichDao().insert(sandwich);
      }
    }).start();
  }

  public void addResponse (final Response response){

    new Thread(new Runnable() {
      @Override
      public void run() {
        SandyDatabase db =  SandyDatabase.getInstance(getApplication());
        db.responseDao().insert(response);
      }
    }).start();

  }

  public void updateHumanEat(final Sandwich sandwich){

    new Thread(new Runnable() {
      @Override
      public void run() {
        SandyDatabase db = SandyDatabase.getInstance(getApplication());
        db.sandwichDao().updateHumanEat(sandwich);
      }
    }).start();

  }

  public LiveData<Sandwich> getSandwich(Long sandwichId) {
    SandyDatabase db = SandyDatabase.getInstance(getApplication());
    return db.sandwichDao().findById(sandwichId);
  }
}

