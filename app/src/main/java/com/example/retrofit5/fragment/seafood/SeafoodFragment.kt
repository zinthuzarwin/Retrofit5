package com.example.retrofit5.viewmodel.seafood

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
import kotlinx.android.synthetic.main.fragment_seafood.*

class SeaFoodFragment : Fragment() {
    private lateinit var mealListAdapter: MealAdapter
    private lateinit var seafoodViewModel: SeafoodViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_seafood, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(activity,2)
        mealListAdapter = MealAdapter()
        recyclerSeaFood.apply {
            adapter = mealListAdapter
            layoutManager = viewManager
            observeViewModel()
        }
    }

    override fun onResume() {
        super.onResume()
        seafoodViewModel.loadSeafood("Seafood")
    }


    private fun observeViewModel() {
        seafoodViewModel = ViewModelProvider(this).get(SeafoodViewModel::class.java)

        seafoodViewModel.getMealList().observe(
            viewLifecycleOwner, Observer {
                mealListAdapter.updateMealList((it))
                Log.d("Seafood UpdateList>>>",it.toString())
            })

    }

}