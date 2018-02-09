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

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {
    private static final String TAG = "BodyPartFragment";

    private static final String IMAGE_IDS = "imageIds";
    private static final String LIST_INDEX = "listIndex";

    private List<Integer> imageIds;
    private int listIndex;

    // Default constructor
    public BodyPartFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            imageIds = savedInstanceState.getIntegerArrayList(IMAGE_IDS);
            listIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        if (imageIds != null) {
            imageView.setImageResource(imageIds.get(this.listIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listIndex < imageIds.size() - 1) {
                        listIndex++;
                    } else {
                        listIndex = 0;
                    }
                    imageView.setImageResource(imageIds.get(listIndex));
                }
            });
        } else {
            Log.e(TAG, "This fragment has no valid imageIds");
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(IMAGE_IDS, (ArrayList<Integer>) imageIds);
        outState.putInt(LIST_INDEX, listIndex);
    }

    public void setImageIds(List<Integer> imageIds) {
        this.imageIds = imageIds;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }
}
