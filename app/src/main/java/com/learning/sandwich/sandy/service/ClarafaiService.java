package com.learning.sandwich.sandy.service;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import androidx.navigation.Navigation;
import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.Model;
import clarifai2.dto.model.ModelVersion;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.model.output_info.ConceptOutputInfo;
import clarifai2.dto.prediction.Concept;
import clarifai2.dto.prediction.Prediction;
import com.learning.sandwich.sandy.MainActivity;
import com.learning.sandwich.sandy.R;
import com.learning.sandwich.sandy.model.Sandwich;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class ClarafaiService {

  static final int REQUEST_TAKE_PHOTO = 1;
  private static final double CONFIDENCE_THRESHOLD = 0.50;
  private HttpLoggingInterceptor.Logger logger;
  Context context;
  private String modelId;
  private boolean trained;
  private String modelVersionId;

  final ClarifaiClient client = new ClarifaiBuilder("132545b0eb9b4f339c26f90afd403fff")
      .client(new OkHttpClient.Builder()
          .connectTimeout(60, TimeUnit.SECONDS)
          .readTimeout(60, TimeUnit.SECONDS)
          .writeTimeout(60, TimeUnit.SECONDS)
          .addInterceptor(new HttpLoggingInterceptor())
          .build()
      )
      .buildSync();




  public class ClarifaiMakeModel extends
      AsyncTask<Sandwich, Void, ClarifaiResponse<List<ClarifaiInput>>> {


    @Override
    protected ClarifaiResponse<List<ClarifaiInput>> doInBackground(Sandwich... sandwiches) {
      List<ClarifaiInput> inputs = new LinkedList<ClarifaiInput>();
      for (Sandwich sandwich : sandwiches) {
        ClarifaiInput input = ClarifaiInput.forImage(sandwich.getFileName())
            .withConcepts(Concept.forID("sandwich"));
        inputs.add(input);
      }
      return client.addInputs()
          .plus(inputs)
          .executeSync();
    }

    @Override
    protected void onPostExecute(ClarifaiResponse<List<ClarifaiInput>> response) {
      if (response.isSuccessful()) {

      }
    }
  }

  private class CreateModel extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
      ClarifaiResponse<ConceptModel> response = client.createModel("sandwich")
          .withOutputInfo(ConceptOutputInfo.forConcepts(
              Concept.forID("sandwich")
          ))
          .executeSync();
      if (response.isSuccessful()) {
        return response.get().id();
      } else {
        cancel(true);
        return null;
      }
    }


    @Override
    protected void onPostExecute(String s) {
      modelId = s;
      new ClarifaiTrainModel().execute();
    }


  }


  public class ClarifaiTrainModel extends AsyncTask<Void, Void, Model<?>> {


    @Override
    protected Model<?> doInBackground(Void... voids) {

      ClarifaiResponse<Model<?>> response = client.trainModel(modelId).executeSync();
      if (!response.isSuccessful()) {
        cancel(true);
        return null;
      } else {
        return response.get();
      }
    }

    @Override
    protected void onPostExecute(Model<?> model) {
      trained = true;
      modelVersionId = model.modelVersion().id();
//      Navigation.findNavController(, R.id.nav_host_fragment)
//          .navigate(R.id.action_responseFragment_to_sandwichImageFragment);
    }
  }

  private class ClarifaiTask extends AsyncTask<File, Void, Boolean> {

    private final PredictionListener listener;

    private ClarifaiTask(
        PredictionListener listener) {
      this.listener = listener;
    }


    protected Boolean doInBackground(File... images) {
      boolean match = false;
      List<ClarifaiOutput<Concept>> predictionResults;
      File image = images[0];
      ModelVersion modelVersion = client.getModelVersionByID(modelId, modelVersionId)
          .executeSync()
          .get();

      ClarifaiResponse<List<ClarifaiOutput<Prediction>>> response = client.predict(modelId)
          .withVersion(modelVersion)
          .withInputs(ClarifaiInput.forImage(image))
          .withMinValue(CONFIDENCE_THRESHOLD)
          .executeSync();
      if (response.isSuccessful()) {
        List<Prediction> predictions = response.get().get(0).data();
        if (predictions != null && !predictions.isEmpty()) {
          Concept concept = predictions.get(0).asConcept().withName("sandwich");
          if (concept != null) {
            match = true;
          }
        }

      } else {
        cancel(true);
      }
      return match;

    }

    protected void onPostExecute(Boolean result) {
      listener.onPrediction(result);
    }

  }


  public interface PredictionListener{

    void onPrediction(boolean match);

  }

}

