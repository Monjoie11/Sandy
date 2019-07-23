package com.learning.sandwich.sandy;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.learning.sandwich.sandy.model.Response;
import com.learning.sandwich.sandy.model.Sandwich;
import com.learning.sandwich.sandy.model.dao.SandwichDao;
import java.util.List;

public class ResponseViewModel extends AndroidViewModel {

  private SandyDatabase db;
  private SandwichDao dao;

  public ResponseViewModel(@NonNull Application application) {
    super(application);
    db = SandyDatabase.getInstance(application);
    dao = db.sandwichDao();
  }

  public LiveData<List<Sandwich>> getSandwiches() {
    return dao.getAll();
  }

  public void updateHumanEat(final Sandwich sandwich) {

    new Thread(() -> dao.updateHumanEat(sandwich)).start();

  }

  public LiveData<Sandwich> getSandwich(Long sandwichId) {
    return dao.findById(sandwichId);
  }

  public LiveData<List<Sandwich>> getTrainingSandwiches() {
    return dao.getAllForTraining();
  }

  // after on click caseR.id.picture(); new ClarafaiTask(getContext(), playerId, SceneId).execute(mFile){

}

