package com.example.dom.data.models

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dom.R
import com.example.dom.data.viewmodels.LandPageViewModel

class LandPage : Fragment() {

    companion object {
        fun newInstance() = LandPage()
    }

    private val viewModel: LandPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_land_page, container, false)
    }
}