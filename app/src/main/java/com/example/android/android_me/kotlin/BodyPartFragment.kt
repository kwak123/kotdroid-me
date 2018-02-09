package com.example.android.android_me.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.android.android_me.R
import java.util.ArrayList

class BodyPartFragment : Fragment() {

    companion object {
        val TAG: String = "BodyPartFragment"
        val IMAGE_IDS: String = "imageIds"
        const val LIST_INDEX: String = "listIndex"
    }

    private lateinit var imageView: ImageView

    private lateinit var imageIds: List<Int>
    private var listIndex: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState != null) {
            imageIds = savedInstanceState.getIntegerArrayList(IMAGE_IDS)
            listIndex = savedInstanceState.getInt(LIST_INDEX)
        }

        val rootView: View = inflater?.inflate(R.layout.fragment_body_part, container, false) as View
        imageView = rootView.findViewById(R.id.body_part_image_view) as ImageView

        imageView.setImageResource(imageIds?.get(listIndex))
        imageView.setOnClickListener(ImageViewClickListener())

        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(IMAGE_IDS, imageIds as ArrayList<Int>)
        outState.putInt(LIST_INDEX, listIndex)
    }

    private inner class ImageViewClickListener: View.OnClickListener {
        override fun onClick(view: View?) {
            imageView.setImageResource(imageIds.get(listIndex))
        }
    }
}