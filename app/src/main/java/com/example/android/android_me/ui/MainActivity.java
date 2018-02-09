package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex = 0;
    private int bodyIndex = 0;
    private int legIndex = 0;

    private static final int HEAD = 0;
    private static final int BODY = 1;
    private static final int LEG = 2;

    private boolean twoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check for tablet
        twoPane = findViewById(R.id.android_me_linear_layout) != null;

        if (twoPane) {
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {

                // Head
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                headFragment.setListIndex(headIndex);

                // Body
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                bodyFragment.setListIndex(bodyIndex);


                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                legFragment.setListIndex(legIndex);

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .add(R.id.body_container, bodyFragment)
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onImageSelected(int imagePosition) {
        int partType = imagePosition / 12;
        int partIndex = imagePosition - 12 * partType;

        if (twoPane) {
            BodyPartFragment bodyPartFragment = new BodyPartFragment();

            switch (partType) {
                case HEAD:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setListIndex(partIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, bodyPartFragment)
                            .commit();
                    break;
                case BODY:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());
                    bodyPartFragment.setListIndex(partIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, bodyPartFragment)
                            .commit();
                    break;
                case LEG:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getLegs());
                    bodyPartFragment.setListIndex(partIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, bodyPartFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {
            switch (partType) {
                case HEAD:
                    headIndex = partIndex;
                    break;
                case BODY:
                    bodyIndex = partIndex;
                    break;
                case LEG:
                    legIndex = partIndex;
                    break;
                default:
                    break;
            }

            Bundle bundle = new Bundle();
            bundle.putInt(AndroidMeActivity.HEAD_TAG, headIndex);
            bundle.putInt(AndroidMeActivity.BODY_TAG, bodyIndex);
            bundle.putInt(AndroidMeActivity.LEG_TAG, legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(bundle);

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
}
