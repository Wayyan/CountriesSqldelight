package com.wayyan.countriessqldelight.adapter

import android.content.Context
import android.view.ViewGroup
import com.wayyan.countriessqldelight.base.core.BaseRecyclerAdapter
import com.wayyan.countriessqldelight.base.core.BaseRecyclerViewHolder
import com.wayyan.countriessqldelight.base.core.Code
import com.wayyan.countriessqldelight.databinding.ItemCountryBinding
import com.wayyan.countriessqldelight.domain.model.CountryModel

class CountryRecyclerAdapter constructor(private val context: Context) :
    BaseRecyclerAdapter<CountryRecyclerAdapter.CountryViewHolder, CountryModel>(context) {

    inner class CountryViewHolder constructor(private val binding: ItemCountryBinding) :
        BaseRecyclerViewHolder<CountryModel>(binding.root) {
        override fun bind(model: CountryModel) {
            binding.tvCountryName.text = model.name
            binding.tvCapitalName.text = model.capital

            binding.root.setOnClickListener {
                delegate?.onAction(Code.IntCode(1), mData[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(mLayoutInflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(mData[position])
    }
}