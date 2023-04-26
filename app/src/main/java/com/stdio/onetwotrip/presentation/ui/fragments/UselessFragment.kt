package com.stdio.onetwotrip.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.stdio.onetwotrip.R
import com.stdio.onetwotrip.common.showSnackbar
import com.stdio.onetwotrip.common.subscribeInUI
import com.stdio.onetwotrip.common.viewBinding
import com.stdio.onetwotrip.databinding.FragmentTicketsBinding
import com.stdio.onetwotrip.databinding.FragmentUselessBinding
import com.stdio.onetwotrip.domain.models.Price
import com.stdio.onetwotrip.domain.models.PriceType
import com.stdio.onetwotrip.domain.models.Ticket
import com.stdio.onetwotrip.presentation.ui.adapter.TicketsAdapter
import com.stdio.onetwotrip.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


class UselessFragment : Fragment(R.layout.fragment_useless) {

    private val binding by viewBinding(FragmentUselessBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNavigateToList.setOnClickListener {
            val action = UselessFragmentDirections.actionUselessFragmentToTicketsFragment()
            findNavController().navigate(action)
        }
    }
}