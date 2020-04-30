package com.example.practice.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.example.practice.entity.CategoryModel
import com.example.practice.entity.CategoryName

class CategoryAdapter(val listOfCategories: List<CategoryModel>,
                      val listener:OnItemCallback
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val model=listOfCategories[position]
        holder.nameTextView.text=model.names[0].translation
        holder.rootLayout.setOnClickListener {
            listener.onCategoryItemClicked(model._id)
        }
    }

    class CategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.item_category_name_text_view)
        val rootLayout:ConstraintLayout=view.findViewById(R.id.item_category_root_layout)
    }
    interface OnItemCallback{
        fun onCategoryItemClicked(categoryId:String)
    }
}