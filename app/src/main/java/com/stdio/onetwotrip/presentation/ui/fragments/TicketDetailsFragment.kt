package com.stdio.onetwotrip.presentation.ui.fragments

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.stdio.onetwotrip.R
import com.stdio.onetwotrip.common.subscribeInUI
import com.stdio.onetwotrip.common.viewBinding
import com.stdio.onetwotrip.databinding.FragmentTicketDetailsBinding
import com.stdio.onetwotrip.domain.models.PriceType
import com.stdio.onetwotrip.presentation.viewmodel.TicketDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketDetailsFragment : Fragment(R.layout.fragment_ticket_details) {

    private val binding by viewBinding(FragmentTicketDetailsBinding::bind)
    private val args: TicketDetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<TicketDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ticket = args.ticket
        val selectedPrice = args.selectedPrice
        val ticketFrom = ticket.trips[0].from
        val ticketTo = ticket.trips[ticket.trips.size - 1].to
        viewModel.getAirportInfo(ticketFrom, ticketTo)
        subscribeObservers()

        if (selectedPrice.type == PriceType.ECONOMY) {
            binding.tvPrice.text =
                getString(R.string.price_economy, selectedPrice.amount, ticket.currency)
        } else {
            binding.tvPrice.text =
                getString(R.string.price_business, selectedPrice.amount, ticket.currency)
        }
        var route = ticket.trips[0].from
        for (trip in ticket.trips) {
            route += " -> ${trip.to}"
        }
        binding.tvRoute.text = getString(R.string.route, route)
        binding.tvTransfers.text = getString(R.string.transfers_count, ticket.trips.size - 1)
    }

    private fun subscribeObservers() {
        viewModel.uiStateFrom.subscribeInUI(this, binding.progressBar) {
            binding.tvDepartureAirport.text =
                Html.fromHtml(
                    getString(
                        R.string.departure_airport,
                        "(${args.ticket.trips[0].from}) ${it.result.name}"
                    ), HtmlCompat.FROM_HTML_MODE_LEGACY
                )
        }
        viewModel.uiStateTo.subscribeInUI(this, binding.progressBar) {
            binding.tvLandingAirport.text =
                Html.fromHtml(
                    getString(
                        R.string.landing_airport,
                        "(${args.ticket.trips[args.ticket.trips.size - 1].to}) ${it.result.name}"
                    ), HtmlCompat.FROM_HTML_MODE_LEGACY
                )
        }
    }
}