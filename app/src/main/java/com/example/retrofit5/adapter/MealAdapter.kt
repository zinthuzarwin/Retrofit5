package com.example.retrofit5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit5.R
import com.example.retrofit5.model.Meal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_food.view.*

class MealAdapter(var mealList: List<Meal> = emptyList()) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindMeal(meal: Meal) {
            itemView.txtFoodTitle.text = meal.strMeal
            Picasso.get().load(meal.strMealThumb).placeholder(R.drawable.ic_launcher_background)
                .into(itemView.imgFood)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return MealViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bindMeal(mealList[position])
    }

    fun updateMealList(mealList: List<Meal>) {
        this.mealList = mealList
        notifyDataSetChanged()
    }
}