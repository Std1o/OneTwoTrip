package com.stdio.onetwotrip.presentation.ui.fragments

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stdio.onetwotrip.R
import com.stdio.onetwotrip.common.subscribeInUI
import com.stdio.onetwotrip.common.viewBinding
import com.stdio.onetwotrip.databinding.FragmentTicketsBinding
import com.stdio.onetwotrip.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class TicketsFragment : Fragment(R.layout.fragment_tickets) {

    private val viewModel by viewModels<MainViewModel>()
    private val binding by viewBinding(FragmentTicketsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.uiState.subscribeInUI(this, binding.progressBar) {
            println(it)
        }
    }
}