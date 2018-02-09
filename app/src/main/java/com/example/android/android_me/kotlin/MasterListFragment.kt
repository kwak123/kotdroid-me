package com.example.android.android_me.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.android.android_me.R

class MasterListFragment : Fragment() {

    interface OnImageClickListener {
        fun onImageSelected(imagePosition: Int)
    }

    companion object {
        val TAG = "MasterListFragment"
    }

    lateinit var imageClickListener: OnImageClickListener

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater?.inflate(R.layout.fragment_master_list, container, false) as View
        val gridView: GridView = rootView.findViewById(R.id.images_grid_view) as GridView


        return rootView
    }

}
