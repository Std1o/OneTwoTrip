package com.stdio.onetwotrip.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stdio.onetwotrip.R
import com.stdio.onetwotrip.databinding.ItemTicketBinding
import com.stdio.onetwotrip.domain.models.PriceType
import com.stdio.onetwotrip.domain.models.Ticket

class TicketsAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<TicketsAdapter.CourseViewHolder>() {

    private var dataList = emptyList<Ticket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemTicketBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    fun setDataList(dataList: List<Ticket>) {
        this.dataList = dataList
        notifyItemRangeChanged(0, dataList.size)
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        with(holder) {
            val ticket = dataList[position]
            val context = holder.itemView.context
            val prices = ticket.prices.sortedBy { it.amount }
            if (prices.size == 1) {
                binding.tvPrices.text = context.getString(R.string.price, prices[0].amount, ticket.currency)
            } else {
                binding.tvPrices.text = context.getString(R.string.prices, prices[0].amount, ticket.currency, prices[1].amount, ticket.currency)
            }
            binding.tvTransfers.text = context.getString(R.string.transfers_count, ticket.trips.size - 1)
            binding.tvDepartureAirport.text = context.getString(R.string.departure_airport, ticket.trips[0].from)
            binding.tvLandingAirport.text = context.getString(R.string.landing_airport, ticket.trips[ticket.trips.size-1].to)
        }
    }

    inner class CourseViewHolder(val binding: ItemTicketBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ClickListener {
        fun onClick(ticket: Ticket)
    }
}