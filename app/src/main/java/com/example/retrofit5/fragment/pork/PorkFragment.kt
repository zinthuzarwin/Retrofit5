package com.example.retrofit5.fragment.pork

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
import kotlinx.android.synthetic.main.fragment_pork.*

class PorkFragment : Fragment() {
    private lateinit var porkListAdapter: MealAdapter
    private lateinit var porkViewModel: MealViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pork, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(activity,2)
        porkListAdapter = MealAdapter()
        recyclerPork.apply {
            adapter = porkListAdapter
            layoutManager = viewManager
            observeViewModel()
        }
    }

    override fun onResume() {
        super.onResume()
        porkViewModel.loadFood("pork")

    }

    private fun observeViewModel() {
        porkViewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        porkViewModel.getMealList().observe(
            viewLifecycleOwner, Observer {
                porkListAdapter.updateMealList((it))
                Log.d("Pork UpdateList>>>",it.toString())
            })

    }

}
