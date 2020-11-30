package com.horizon.testdaggerapplication.view

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.horizon.testdaggerapplication.R
import com.horizon.testdaggerapplication.data.model.Restaurant
import com.horizon.testdaggerapplication.databinding.ActivityMainBinding
import com.horizon.testdaggerapplication.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RestaurantAdapter.RestaurantItemListener {

    private lateinit var dataBinding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var adapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.lifecycleOwner = this
        setupObservers()
        setupRecyclerView()

        val jsonString = getJsonDataFromAsset(this, "sample.json")
        jsonString?.let {
            viewModel.parseJsonString(it)
        }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun setupRecyclerView() {
        adapter = RestaurantAdapter(this)
        dataBinding.rvRestaurant.layoutManager = LinearLayoutManager(this)
        dataBinding.rvRestaurant.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.restaurant.observe(this, {
            if (it.isNullOrEmpty().not()) adapter.setItems(it)
        })
    }

    override fun onClickedRestaurant(restaurant: Restaurant) {
        Toast.makeText(this, "You Selected ${restaurant.name} Restaurant", Toast.LENGTH_SHORT)
            .show()
    }
}