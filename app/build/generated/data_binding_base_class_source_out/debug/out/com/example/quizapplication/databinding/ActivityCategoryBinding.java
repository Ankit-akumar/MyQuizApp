// Generated by view binder compiler. Do not edit!
package com.example.quizapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.quizapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCategoryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView cvAptitudeTest;

  @NonNull
  public final CardView cvReasoningTest;

  @NonNull
  public final CardView cvVerbalTest;

  @NonNull
  public final ConstraintLayout drawer;

  @NonNull
  public final TextView tvChooseCategory;

  private ActivityCategoryBinding(@NonNull ConstraintLayout rootView,
      @NonNull CardView cvAptitudeTest, @NonNull CardView cvReasoningTest,
      @NonNull CardView cvVerbalTest, @NonNull ConstraintLayout drawer,
      @NonNull TextView tvChooseCategory) {
    this.rootView = rootView;
    this.cvAptitudeTest = cvAptitudeTest;
    this.cvReasoningTest = cvReasoningTest;
    this.cvVerbalTest = cvVerbalTest;
    this.drawer = drawer;
    this.tvChooseCategory = tvChooseCategory;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCategoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCategoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_category, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCategoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cv_aptitude_test;
      CardView cvAptitudeTest = rootView.findViewById(id);
      if (cvAptitudeTest == null) {
        break missingId;
      }

      id = R.id.cv_reasoning_test;
      CardView cvReasoningTest = rootView.findViewById(id);
      if (cvReasoningTest == null) {
        break missingId;
      }

      id = R.id.cv_verbal_test;
      CardView cvVerbalTest = rootView.findViewById(id);
      if (cvVerbalTest == null) {
        break missingId;
      }

      ConstraintLayout drawer = (ConstraintLayout) rootView;

      id = R.id.tv_choose_category;
      TextView tvChooseCategory = rootView.findViewById(id);
      if (tvChooseCategory == null) {
        break missingId;
      }

      return new ActivityCategoryBinding((ConstraintLayout) rootView, cvAptitudeTest,
          cvReasoningTest, cvVerbalTest, drawer, tvChooseCategory);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}