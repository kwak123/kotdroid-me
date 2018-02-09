package com.example.android.android_me.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.GridView
import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets
import com.example.android.android_me.kotlin.MasterListFragment.OnImageClickListener

class MainActivity : AppCompatActivity(), OnImageClickListener {

    companion object {
        private val HEAD: Int = 0
        private val BODY: Int = 1
        private val LEG: Int = 2
    }

    var headIndex: Int = 0
    var bodyIndex: Int = 0
    var legIndex: Int = 0
    var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        twoPane = findViewById(R.id.android_me_linear_layout) != null

        if (twoPane) {
            val nextButton: Button = findViewById(R.id.next_button) as Button
            nextButton.visibility = View.GONE

            val gridView: GridView = findViewById(R.id.images_grid_view) as GridView
            gridView.numColumns = 2

            if (savedInstanceState == null) {
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
        }
    }

    override fun onImageSelected(imagePosition: Int) {
        val partType: Int = imagePosition / 12
        val partIndex: Int = imagePosition - 12 * partType

        if (twoPane) {
            val newFragment = BodyPartFragment()
            newFragment.setListIndex(partIndex)

            when (partType) {
                HEAD -> {
                    newFragment.setImageIds(AndroidImageAssets.getHeads())
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit()
                }
                BODY -> {
                    newFragment.setImageIds(AndroidImageAssets.getBodies())
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit()
                }
                LEG -> {
                    newFragment.setImageIds(AndroidImageAssets.getLegs())
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit()
                }
                else -> {}

            }
        } else {
            when (partType) {
                HEAD -> headIndex = partIndex
                BODY -> bodyIndex = partIndex
                LEG -> legIndex = partIndex
                else -> {}
            }

            val bundle = Bundle()
            bundle.putInt(AndroidMeActivity.HEAD_TAG, headIndex)
            bundle.putInt(AndroidMeActivity.BODY_TAG, bodyIndex)
            bundle.putInt(AndroidMeActivity.LEG_TAG, legIndex)

            val intent = Intent(this, AndroidMeActivity::class.java)
            intent.putExtras(bundle)

            val nextButton: Button = findViewById(R.id.next_button) as Button
            nextButton.setOnClickListener({ _ -> startActivity(intent) })
        }
    }
}