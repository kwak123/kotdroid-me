package com.example.android.android_me.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.GridView
import com.example.android.android_me.R

class MainActivity : AppCompatActivity() {

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

            }
        }
    }
}