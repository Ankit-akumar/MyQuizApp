// Generated by view binder compiler. Do not edit!
package com.example.quizapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.quizapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageButton btnEndQuiz;

  @NonNull
  public final Button btnNext;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final TextView tvClearResponse;

  @NonNull
  public final TextView tvOption1;

  @NonNull
  public final TextView tvOption2;

  @NonNull
  public final TextView tvOption3;

  @NonNull
  public final TextView tvOption4;

  @NonNull
  public final TextView tvProgressBar;

  @NonNull
  public final TextView tvQuestion;

  private ActivityMainBinding(@NonNull ScrollView rootView, @NonNull ImageButton btnEndQuiz,
      @NonNull Button btnNext, @NonNull ProgressBar progressBar, @NonNull TextView tvClearResponse,
      @NonNull TextView tvOption1, @NonNull TextView tvOption2, @NonNull TextView tvOption3,
      @NonNull TextView tvOption4, @NonNull TextView tvProgressBar, @NonNull TextView tvQuestion) {
    this.rootView = rootView;
    this.btnEndQuiz = btnEndQuiz;
    this.btnNext = btnNext;
    this.progressBar = progressBar;
    this.tvClearResponse = tvClearResponse;
    this.tvOption1 = tvOption1;
    this.tvOption2 = tvOption2;
    this.tvOption3 = tvOption3;
    this.tvOption4 = tvOption4;
    this.tvProgressBar = tvProgressBar;
    this.tvQuestion = tvQuestion;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_end_quiz;
      ImageButton btnEndQuiz = rootView.findViewById(id);
      if (btnEndQuiz == null) {
        break missingId;
      }

      id = R.id.btn_next;
      Button btnNext = rootView.findViewById(id);
      if (btnNext == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = rootView.findViewById(id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.tv_clear_response;
      TextView tvClearResponse = rootView.findViewById(id);
      if (tvClearResponse == null) {
        break missingId;
      }

      id = R.id.tv_option1;
      TextView tvOption1 = rootView.findViewById(id);
      if (tvOption1 == null) {
        break missingId;
      }

      id = R.id.tv_option2;
      TextView tvOption2 = rootView.findViewById(id);
      if (tvOption2 == null) {
        break missingId;
      }

      id = R.id.tv_option3;
      TextView tvOption3 = rootView.findViewById(id);
      if (tvOption3 == null) {
        break missingId;
      }

      id = R.id.tv_option4;
      TextView tvOption4 = rootView.findViewById(id);
      if (tvOption4 == null) {
        break missingId;
      }

      id = R.id.tv_progress_bar;
      TextView tvProgressBar = rootView.findViewById(id);
      if (tvProgressBar == null) {
        break missingId;
      }

      id = R.id.tv_question;
      TextView tvQuestion = rootView.findViewById(id);
      if (tvQuestion == null) {
        break missingId;
      }

      return new ActivityMainBinding((ScrollView) rootView, btnEndQuiz, btnNext, progressBar,
          tvClearResponse, tvOption1, tvOption2, tvOption3, tvOption4, tvProgressBar, tvQuestion);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
