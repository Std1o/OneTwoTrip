package com.stdio.onetwotrip.presentation.ui.fragments

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
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
import com.stdio.onetwotrip.domain.models.Price
import com.stdio.onetwotrip.domain.models.PriceType
import com.stdio.onetwotrip.domain.models.Ticket
import com.stdio.onetwotrip.presentation.ui.adapter.TicketsAdapter
import com.stdio.onetwotrip.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.ticker
import java.io.File


@AndroidEntryPoint
class TicketsFragment : Fragment(R.layout.fragment_tickets) {

    private val viewModel by viewModels<MainViewModel>()
    private val binding by viewBinding(FragmentTicketsBinding::bind)
    private lateinit var adapter: TicketsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TicketsAdapter {
            showTariffSelectionDialog(it)
        }
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.uiState.subscribeInUI(this, binding.progressBar) {
            adapter.setDataList(it)
        }
    }

    private fun showTariffSelectionDialog(ticket: Ticket) {
        val prices = ticket.prices
        val currency = ticket.currency
        val builder = AlertDialog.Builder(requireContext())
        val priceEconomy = prices.firstOrNull { it.type == PriceType.ECONOMY }
        val priceBusiness = prices.firstOrNull { it.type == PriceType.BUSINESS }
        val items = arrayOf(
            getString(R.string.price_economy, priceEconomy?.amount, currency),
            getString(R.string.price_business, priceBusiness?.amount, currency),
        )
        builder.setSingleChoiceItems(items, -1) { dialog, item ->
                Toast.makeText(
                    activity, "Выбрано:  ${items[item]}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setPositiveButton(
                "OK"
            ) { dialog, id ->
                val action = TicketsFragmentDirections.actionTicketsFragmentToTicketDetailsFragment(ticket)
                findNavController().navigate(action)
            }
            .setNegativeButton("Отмена") { dialog, id ->
            }
        builder.create().show()
    }
}