package com.learning.sandwich.sandy;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import com.google.android.material.snackbar.Snackbar;
import com.learning.sandwich.sandy.model.Sandwich;
import com.learning.sandwich.sandy.service.ClarifaiService;
import java.util.List;
import java.util.Random;

public class ResponseFragment extends Fragment {


  private ImageButton yesButton;
  private ImageButton noButton;
  private Random rng = new Random();
  private ResponseViewModel viewModel;
  private ImageView responseImage;
  private TextView responseText;
  private SharedPreferences sharedPref;
  private Boolean tutorialComplete;
  private SharedPreferences.Editor editor;
  private int tutorialPosition = 0;
  private List<Sandwich> sandwiches;
  private Sandwich sandwich;
  private int malingeringCount = 0;

  public static ResponseFragment newInstance() {
    return new ResponseFragment();
  } // Instance needed for several methods and database interactions

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    viewModel = ViewModelProviders.of(getActivity()).get(ResponseViewModel.class);
    viewModel.getSandwiches().observe(this, (sandwiches) -> this.sandwiches = sandwiches);
    View view = inflater.inflate(R.layout.response_fragment, container, false);
    sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
    responseImage = view.findViewById(R.id.captured_sandwich);
    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.test_image1);
    responseImage.setImageBitmap(bmp);
    noButton = view.findViewById(R.id.no_button);
    noButton.setOnClickListener(v -> {
      if (!sharedPref
          .getBoolean(getString(R.string.saved_tutorial_complete_key), false)) {
        switch (tutorialPosition) {
          case 0:
            Snackbar snackbarNo = Snackbar
                .make(view, getString(R.string.tutorial_no0), Snackbar.LENGTH_LONG);
            snackbarNo.show();
            break;
          case 1:
            Snackbar snackbarNo1 = Snackbar
                .make(view, getString(R.string.tutorial_no1), Snackbar.LENGTH_LONG);
            snackbarNo1.show();
            malingeringCount++;
            doTutorial(tutorialPosition++);
            break;
          case 2:
            Snackbar snackbarNo2 = Snackbar
                .make(view, getString(R.string.tutorial_no2), Snackbar.LENGTH_LONG);
            snackbarNo2.show();
            notASandwich(viewModel);
            break;
          case 3:
            Snackbar snackbarNo3 = Snackbar
                .make(view, getString(R.string.tutorial_no3), Snackbar.LENGTH_LONG);
            snackbarNo3.show();
            notASandwich(viewModel);
            break;
          case 4:
            Snackbar snackbarNo4 = Snackbar
                .make(view, getString(R.string.tutorial_no4), Snackbar.LENGTH_LONG);
            snackbarNo4.show();
            sandwich.setHumanEat(false);
            viewModel.updateHumanEat(sandwich);
            doTutorial(tutorialPosition++);
            break;
          case 5:
            Snackbar snackbarNo5 = Snackbar
                .make(view, getString(R.string.tutorial_no5), Snackbar.LENGTH_LONG);
            snackbarNo5.show();
            notASandwich(viewModel);
            break;
          case 6:
            Snackbar snackbarNo6 = Snackbar
                .make(view, getString(R.string.tutorial_no6), Snackbar.LENGTH_LONG);
            snackbarNo6.show();
            notASandwich(viewModel);
            break;
          case 7:
            Snackbar snackbarNo7 = Snackbar
                .make(view, getString(R.string.tutorial_no7), Snackbar.LENGTH_LONG);
            snackbarNo7.show();
            notASandwich(viewModel);
          case 8:
            Snackbar snackbarNo8 = Snackbar
                .make(view, getString(R.string.tutorial_no8), Snackbar.LENGTH_LONG);
            snackbarNo8.show();
            sandwich.setHumanEat(false);
            viewModel.updateHumanEat(sandwich);
            doTutorial(tutorialPosition++);
            break;
          case 9:
            Snackbar snackbarNo9 = Snackbar
                .make(view, getString(R.string.tutorial_no9), Snackbar.LENGTH_LONG);
            snackbarNo9.show();
            notASandwich(viewModel);
            break;
          case 10:
            Snackbar snackbarNo10 = Snackbar
                .make(view, getString(R.string.tutorial_no10), Snackbar.LENGTH_LONG);
            snackbarNo10.show();
            notASandwich(viewModel);
            break;
          case 11:
            Snackbar snackbarNo11 = Snackbar
                .make(view, getString(R.string.tutorial_no11), Snackbar.LENGTH_LONG);
            snackbarNo11.show();
            notASandwich(viewModel);
            break;
          case 12:
            endOfTutorial(viewModel, view);
        }
      }
      // TODO function of button when not in tutorial
    });
    yesButton = view.findViewById(R.id.yes_button);
    yesButton.setOnClickListener(v -> {
      if (!sharedPref
          .getBoolean(getString(R.string.saved_tutorial_complete_key), false)) {
        switch (tutorialPosition) {
          case 0:
            Snackbar snackbarYes = Snackbar
                .make(view, getString(R.string.tutorial_yes0), Snackbar.LENGTH_LONG);
            snackbarYes.show();
            doTutorial(tutorialPosition++);
            break;
          case 1:
            Snackbar snackbarYes1 = Snackbar
                .make(view, getString(R.string.tutorial_yes1), Snackbar.LENGTH_LONG);
            snackbarYes1.show();
            itIsASandwich(viewModel);
            break;
          case 2:
            Snackbar snackbarYes2 = Snackbar
                .make(view, getString(R.string.tutorial_yes2), Snackbar.LENGTH_LONG);
            snackbarYes2.show();
            itIsASandwich(viewModel);
            break;
          case 3:
            Snackbar snackbarYes3 = Snackbar
                .make(view, getString(R.string.tutorial_yes3), Snackbar.LENGTH_LONG);
            snackbarYes3.show();
            itIsASandwich(viewModel);
            break;
          case 4:
            Snackbar snackbarYes4 = Snackbar
                .make(view, getString(R.string.tutorial_yes4), Snackbar.LENGTH_LONG);
            snackbarYes4.show();
            doTutorial(tutorialPosition++);
            malingeringCount++;
            break;
          case 5:
            Snackbar snackbarYes5 = Snackbar
                .make(view, getString(R.string.tutorial_yes5), Snackbar.LENGTH_LONG);
            snackbarYes5.show();
            itIsASandwich(viewModel);
            break;
          case 6:
            Snackbar snackbarYes6 = Snackbar
                .make(view, getString(R.string.tutorial_yes6), Snackbar.LENGTH_LONG);
            snackbarYes6.show();
            itIsASandwich(viewModel);
            break;
          case 7:
            Snackbar snackbarYes7 = Snackbar
                .make(view, getString(R.string.tutorial_yes7), Snackbar.LENGTH_LONG);
            snackbarYes7.show();
            itIsASandwich(viewModel);
            break;
          case 8:
            malingeringCount++;
            Snackbar snackbarYes8 = Snackbar
                .make(view, getString(R.string.tutorial_yes8), Snackbar.LENGTH_LONG);
            snackbarYes8.show();
            doTutorial(tutorialPosition++);
            break;
          case 9:
            Snackbar snackbarYes9 = Snackbar
                .make(view, getString(R.string.tutorial_yes9), Snackbar.LENGTH_LONG);
            snackbarYes9.show();
            itIsASandwich(viewModel);
            break;
          case 10:
            Snackbar snackbarYes10 = Snackbar
                .make(view, getString(R.string.tutorial_yes10), Snackbar.LENGTH_LONG);
            snackbarYes10.show();
            itIsASandwich(viewModel);
            break;
          case 11:
            Snackbar snackbarYes11 = Snackbar
                .make(view, getString(R.string.tutorial_yes11), Snackbar.LENGTH_LONG);
            snackbarYes11.show();
            itIsASandwich(viewModel);
            break;
          case 12:
            endOfTutorial(viewModel, view);
            break;
        }
      }
      // TODO function of button when not in tutorial
    });
    responseText = view.findViewById(R.id.response_text);
    if (!sharedPref.getBoolean(getString(R.string.saved_tutorial_complete_key), false)) {
      responseText.setText(R.string.first_screen);
    }
    return view;
  }


  private void itIsASandwich(ResponseViewModel viewModel) {
    sandwich.setHumanEat(true);
    viewModel.updateHumanEat(sandwich);
    doTutorial(tutorialPosition++);
  }

  private void notASandwich(ResponseViewModel viewModel) {
    sandwich.setHumanEat(false);
    viewModel.updateHumanEat(sandwich);
    doTutorial(tutorialPosition++);
  }

  private String randomTutorialQuestions() {
    String[] answers = getResources().getStringArray(R.array.tutorial_questions);
    return answers[rng.nextInt(answers.length)];
  }

  private void doTutorial(int position) {
    responseText.setText(randomTutorialQuestions());
    sandwich = sandwiches.get(position);
    Drawable drawable;
    Resources res = getResources();
    Bitmap bmp;
    if (sandwich.isImageResource()) {
      int id = res.getIdentifier(sandwich.getFileName(), "drawable", getContext().getPackageName());
      responseImage.setImageDrawable(getContext().getDrawable(id));
    } else {

    }
    //   = BitmapFactory.decodeResource(getResources(), imageArray[position]);
    //  responseImage.setImageBitmap(bmp);
    yesButton.setVisibility(View.VISIBLE);
    noButton.setVisibility(View.VISIBLE);
  }

  private void endOfTutorial(ResponseViewModel viewModel, View view) {
    Snackbar snackbarNo12 = Snackbar
        .make(view, getString(R.string.tutorial_no12), Snackbar.LENGTH_LONG);
    snackbarNo12.show();
    if (malingeringCount > 2) {
      Snackbar snackbarNo13 = Snackbar
          .make(view, getString(R.string.tutorial_no13), Snackbar.LENGTH_LONG);
      snackbarNo13.show();
      tutorialPosition = 0;
    } else {
      Snackbar snackbarNo14 = Snackbar
          .make(view, getString(R.string.tutorial_no14), Snackbar.LENGTH_LONG);
      snackbarNo14.show();
      sandwich.setHumanEat(false);
      viewModel.updateHumanEat(sandwich);
      //    SharedPreferences.Editor editor = sharedPref.edit();
      //     editor.putBoolean(getString(R.string.saved_tutorial_complete_key), true);
      //     editor.apply();
      tutorialPosition++;
      viewModel.getTrainingSandwiches().observe(this, (sandwiches) -> {
        if (tutorialPosition > 12) {
          Log.d(getClass().getName(), "Observe sandwich for model" + sandwiches.toString());
          ClarifaiService.ClarifaiPutImagesInModel modelMaker = new ClarifaiService.ClarifaiPutImagesInModel();
          modelMaker.execute(sandwiches.toArray(new Sandwich[0]));
        }
      });
//      viewModel.pruneTutorial();
    }
    Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
        .navigate(R.id.action_responseFragment_to_sandwichImageFragment);
  }


}
