package com.example.android.android_me.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets

class AndroidMeActivity : AppCompatActivity() {

    companion object {
        val HEAD_TAG: String = "headIndex"
        val BODY_TAG: String = "bodyIndex"
        val LEG_TAG: String = "legIndex"
    }

    private var headIndex = 0
    private var bodyIndex = 0
    private var legIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_me)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            headIndex = bundle.getInt(HEAD_TAG, 0)
            bodyIndex = bundle.getInt(BODY_TAG, 0)
            legIndex = bundle.getInt(LEG_TAG, 0)
        }

        val headFragment = BodyPartFragment()
        headFragment.setImageIds(AndroidImageAssets.getHeads())
        headFragment.setListIndex(headIndex)

        val bodyFragment = BodyPartFragment()
        bodyFragment.setImageIds(AndroidImageAssets.getBodies())
        bodyFragment.setListIndex(bodyIndex)

        val legFragment = BodyPartFragment()
        legFragment.setImageIds(AndroidImageAssets.getLegs())
        legFragment.setListIndex(legIndex)

        supportFragmentManager.beginTransaction()
                .add(R.id.head_container, headFragment)
                .add(R.id.body_container, bodyFragment)
                .add(R.id.leg_container, legFragment)
                .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(HEAD_TAG, headIndex)
        outState.putInt(BODY_TAG, bodyIndex)
        outState.putInt(LEG_TAG, legIndex)
    }
}
