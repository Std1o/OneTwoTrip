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
import com.stdio.onetwotrip.domain.models.Price
import com.stdio.onetwotrip.domain.models.PriceType
import com.stdio.onetwotrip.domain.models.Ticket
import com.stdio.onetwotrip.presentation.ui.adapter.TicketsAdapter
import com.stdio.onetwotrip.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


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
        val prices = ticket.prices.sortedBy { it.amount }
        val currency = ticket.currency
        val builder = AlertDialog.Builder(requireContext())
        val priceEconomy = prices.firstOrNull { it.type == PriceType.ECONOMY }
        val priceBusiness = prices.firstOrNull { it.type == PriceType.BUSINESS }
        val items = listOfNotNull(
            if (priceEconomy != null) getString(
                R.string.price_economy,
                priceEconomy.amount,
                currency
            ) else null,
            if (priceBusiness != null) getString(
                R.string.price_business,
                priceBusiness.amount,
                currency
            ) else null,
        ).toTypedArray()
        var selectedPrice: Price? = null
        builder.setSingleChoiceItems(items, -1) { dialog, itemPos ->
            selectedPrice = prices[itemPos]
            }
            .setPositiveButton(
                "OK"
            ) { dialog, id ->
                if (selectedPrice != null) {
                    val action = TicketsFragmentDirections.actionTicketsFragmentToTicketDetailsFragment(ticket, selectedPrice!!)
                    findNavController().navigate(action)
                } else {
                    showSnackbar(R.string.price_selection_error)
                }
            }
            .setNegativeButton("Отмена") { dialog, id ->
            }
        builder.setCancelable(false)
        builder.create().show()
    }
}