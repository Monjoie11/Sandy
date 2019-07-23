package com.learning.sandwich.sandy.service;

import android.os.AsyncTask;
import android.util.Log;
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
import com.learning.sandwich.sandy.model.Sandwich;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * This gorgeous class is comprised of four asynchronous classes which all make calls to the
 * Clarifai image recognition service. It is a singleton, in that only one instance of the object
 * can exist. All fields and nested classes are static and are shared by subclasses
 */
public class ClarifaiService {


  private static final double CONFIDENCE_THRESHOLD = 0.50;
  private static String modelId;
  private static boolean trained;
  private static String modelVersionId;

  static {
    // TODO Read modelId & modelVersionId from shared preferences, with a default of null. If the
    //  values are non-null, set trained = true.

  }


  /**
   * This is the Clarfai client used for interacting wth the Clarifai service with  superfluous
   * OKHttp interceptor inserted
   */
  static final ClarifaiClient client = new ClarifaiBuilder("9cfe1361e1894fa2abdfafa4929b07ed")
      .client(new OkHttpClient.Builder()
          .connectTimeout(60, TimeUnit.SECONDS)
          .readTimeout(60, TimeUnit.SECONDS)
          .writeTimeout(60, TimeUnit.SECONDS)
          .addInterceptor(new HttpLoggingInterceptor())
          .build()
      )
      .buildSync();

  private ClarifaiService() {
  }


  /**
   * This class calls an asynchronous method that loads pictures into a given concept in Clarifai.
   * It's onPostExecuteMethod invokes a subsequent asynchronous task that creates a model around
   * those ages, and that class's onPostExecute invokes the final asynchronous task in the chain,
   * which trains the model.
   */
  public static class ClarifaiPutImagesInModel extends
      AsyncTask<Sandwich, Void, ClarifaiResponse<List<ClarifaiInput>>> {


    /**
     * @param sandwiches this method takes in a lst of sandwiches and loads them around a concept to
     * Clarifai. It returns a list of Clarifi Responses
     */
    @Override
    protected ClarifaiResponse<List<ClarifaiInput>> doInBackground(Sandwich... sandwiches) {
      List<ClarifaiInput> inputs = new LinkedList<>();
      for (Sandwich sandwich : sandwiches) {
        ClarifaiInput input = ClarifaiInput.forImage(sandwich.getFileName())
            .withConcepts(Concept.forID("sandwich"));
        inputs.add(input);
      }
      return client.addInputs()
          .plus(inputs)
          .executeSync();
    }

    /**
     * @param response this method is called when the above asynchronous method has terminated. If
     * Clarifai returns that the image load was successful, it call the next asynchronous task
     */
    @Override
    protected void onPostExecute(ClarifaiResponse<List<ClarifaiInput>> response) {
      if (response.isSuccessful()) {
        new ClarafaiCreateModel().execute();
      } else {
        //TODO figure out how to show a snackbar/toast without reference to a view
      }
    }
  }

  /**
   * This class contains the methods that will tell Clarifai to turn the previously loaded images
   * into a model
   */
  private static class ClarafaiCreateModel extends AsyncTask<Void, Void, String> {

    /**
     * @param voids this method tells Clarifai to turn the previously loaded images into a model
     */
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


    /**
     * @param s this method stores the model id for use in downstream functions and call the next
     * asyncTask
     */
    @Override
    protected void onPostExecute(String s) {
      modelId = s;
      // TODO Store modelId in shared preferences.
      Log.d(getClass().getName(), String.format("modelId = %s", modelId));
      new ClarifaiTrainModel().execute();
    }


  }


  /**
   * This cass contains the methods that will train the created model
   */
  private static class ClarifaiTrainModel extends AsyncTask<Void, Void, Model<?>> {


    /**
     * @param voids This method tells Clarifai to train a given model designated by model Id
     */
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

    /**
     * @param model this method stores the model version id of the trained model. The model version
     * id will be used to compare images to a specific trained model
     */
    @Override
    protected void onPostExecute(Model<?> model) {
      trained = true;
      modelVersionId = model.modelVersion().id();
      Log.d(getClass().getName(), String.format("modelVersionId = %s", modelVersionId));
      // TODO Store modelVersionId in shared preferences.
      //TODO figure out how to call fragment navigation on post execute;

      //Notes for later in constuctor create the interface listenerer pass lambda when task constructed 9ffom response fragment
    }
  }

  /**
   * This class will be invoked from the SandwichImageFragment after a picture is taken. It was send
   * that image to clarifai and return a response determining whether or not the image fits the
   * trained sandwich model
   */
  public static class ClarifaiTask extends AsyncTask<File, Void, Boolean> {

    private final PredictionListener listener;

    private ClarifaiTask(
        PredictionListener listener) {
      this.listener = listener;
    }


    /**
     * @param images This method will send a captured image (from SandwichImageFragment) to
     * Calarifai to be compare to a specific, previously trained model. It listens for a response
     * from clarfai determing the images sandwich status
     */
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

    /**
     * @param result this method evokes the listener interface which will be embedded in to parent
     * fragment at the UI level, allowing srrs to interact with the resul.
     */
    protected void onPostExecute(Boolean result) {
      listener.onPrediction(result);
    }

  }


  /**
   * This interface was created to be called from a lambda in the SandwichImageFragment to effect a
   * response to the image classification
   */
  public interface PredictionListener {

    void onPrediction(boolean match);

  }

}

