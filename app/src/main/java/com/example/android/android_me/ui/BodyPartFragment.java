package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.List;

public class BodyPartFragment extends Fragment {
    private static final String TAG = "BodyPartFragment";

    private List<Integer> imageIds;
    private int listIndex;

    // Default constructor
    public BodyPartFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        if (this.imageIds != null) {
            imageView.setImageResource(this.imageIds.get(this.listIndex));
        } else {
            Log.e(TAG, "This fragment has no valid imageIds");
        }

        return rootView;
    }

    public void setImageIds(List<Integer> imageIds) {
        this.imageIds = imageIds;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }
}
