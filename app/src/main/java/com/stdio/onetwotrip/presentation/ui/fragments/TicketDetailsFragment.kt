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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.stdio.onetwotrip.R
import com.stdio.onetwotrip.common.showSnackbar
import com.stdio.onetwotrip.common.subscribeInUI
import com.stdio.onetwotrip.common.viewBinding
import com.stdio.onetwotrip.databinding.FragmentTicketDetailsBinding
import com.stdio.onetwotrip.databinding.FragmentTicketsBinding
import com.stdio.onetwotrip.domain.models.Price
import com.stdio.onetwotrip.domain.models.PriceType
import com.stdio.onetwotrip.presentation.ui.adapter.TicketsAdapter
import com.stdio.onetwotrip.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.ticker
import java.io.File


class TicketDetailsFragment : Fragment(R.layout.fragment_ticket_details) {

    private val binding by viewBinding(FragmentTicketDetailsBinding::bind)
    private val args: TicketDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ticket = args.ticket
        val priceEconomy = ticket.prices.firstOrNull { it.type == PriceType.ECONOMY }
        val priceBusiness = ticket.prices.firstOrNull { it.type == PriceType.BUSINESS }
        binding.tvPriceEconomy.text = getString(R.string.price_economy, priceEconomy?.amount, ticket.currency)
        binding.tvPriceBusiness.text = getString(R.string.price_business, priceBusiness?.amount, ticket.currency)
        binding.tvTransfers.text = getString(R.string.transfers_count, ticket.trips.size - 1)
        binding.tvDepartureAirport.text = getString(R.string.departure_airport, ticket.trips[0].from)
        binding.tvLandingAirport.text = getString(R.string.departure_airport, ticket.trips[ticket.trips.size-1].to)
    }
}