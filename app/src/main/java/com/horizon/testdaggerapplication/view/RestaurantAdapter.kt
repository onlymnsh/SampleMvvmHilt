package com.horizon.testdaggerapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.horizon.testdaggerapplication.data.model.Restaurant
import com.horizon.testdaggerapplication.databinding.ItemRestaurantBinding

class RestaurantAdapter(private val listener: RestaurantItemListener) :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    interface RestaurantItemListener {
        fun onClickedRestaurant(restaurant: Restaurant)
    }

    private var items: List<Restaurant> = mutableListOf()

    fun setItems(items: List<Restaurant>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding: ItemRestaurantBinding =
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) =
        holder.bind(items[position])
}

class RestaurantViewHolder(
    private val itemBinding: ItemRestaurantBinding,
    private val listener: RestaurantAdapter.RestaurantItemListener
) : RecyclerView.ViewHolder(itemBinding.root) {


    fun bind(item: Restaurant) {
        itemBinding.restaurant = item
        itemBinding.listener = listener
        itemBinding.executePendingBindings()
    }
}

