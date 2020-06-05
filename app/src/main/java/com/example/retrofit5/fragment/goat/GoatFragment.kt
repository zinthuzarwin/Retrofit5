package com.example.retrofit5.fragment.goat

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
import kotlinx.android.synthetic.main.fragment_goat.*

class GoatFragment : Fragment() {
    private lateinit var goatListAdapter: MealAdapter
    private lateinit var goatViewModel: MealViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_goat, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(activity,2)
        goatListAdapter = MealAdapter()
        recyclerGoat.apply {
            adapter = goatListAdapter
            layoutManager = viewManager
            observeViewModel()
        }
    }

    override fun onResume() {
        super.onResume()
        goatViewModel.loadFood("goat")
    }

    private fun observeViewModel() {
        goatViewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        goatViewModel.getMealList().observe(
            viewLifecycleOwner, Observer {
                goatListAdapter.updateMealList(it)
                Log.d("Goat UpdateList>>>",it.toString())
            })

    }

}
