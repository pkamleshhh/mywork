package com.example.studentrecords.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.studentrecords.R
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment

/**
 * A simple [Fragment] subclass.
 */
class BlurDialogFragment : SupportBlurDialogFragment() {
    private var viewParent: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewParent = inflater.inflate(R.layout.fragment_blur_dialog, container, false)

        return viewParent
    }
}
