package com.example.retrofit5.viewmodel.beef

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
import com.example.retrofit5.viewmodel.MealViewModel
import kotlinx.android.synthetic.main.fragment_beef.*

class BeefFragment : Fragment() {
    private lateinit var beefListAdapter: MealAdapter
    private lateinit var beefViewModel: MealViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_beef, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(activity,2)
        beefListAdapter = MealAdapter()
        recyclerBeef.apply {
            adapter = beefListAdapter
            layoutManager = viewManager
            observeViewModel()
        }
    }

    override fun onResume() {
        super.onResume()
        beefViewModel.loadFood("beef")
    }

    private fun observeViewModel() {
        beefViewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        beefViewModel.getMealList().observe(
            viewLifecycleOwner, Observer {
                beefListAdapter.updateMealList((it))
                Log.d("Beef UpdateList>>>",it.toString())
            })

    }

}
