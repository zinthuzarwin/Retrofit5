package com.example.retrofit5.viewmodel.chicken

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit5.R
import com.example.retrofit5.adapter.MealAdapter
import kotlinx.android.synthetic.main.fragment_chicken.*

class ChickenFragment : Fragment() {

    private lateinit var chickenListAdapter: MealAdapter
    private lateinit var chickenViewModel: ChickenViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_chicken, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(activity,2)
        chickenListAdapter = MealAdapter()
        recyclerChicken.apply {
            adapter = chickenListAdapter
            layoutManager = viewManager
            observeViewModel()
        }

    }

    override fun onResume() {
        super.onResume()
        chickenViewModel.loadChicken("Chicken")
    }


    private fun observeViewModel() {
        chickenViewModel = ViewModelProvider(this).get(ChickenViewModel::class.java)

        chickenViewModel.getMealList().observe(
            viewLifecycleOwner, Observer {
                chickenListAdapter.updateMealList((it))
                Log.d("Chicken UpdateList>>>",it.toString())
            })

    }

}