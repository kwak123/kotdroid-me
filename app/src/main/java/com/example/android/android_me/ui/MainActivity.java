package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private static final int HEAD = 0;
    private static final int BODY = 1;
    private static final int LEG = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int imagePosition) {
        int partType = imagePosition / 12;
        int partIndex = imagePosition - 12 * partType;

        switch(partType) {
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
