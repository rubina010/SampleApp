package com.example.practice

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.main.CategoryAdapter
import com.example.practice.main.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject

class FragmentCategory : DaggerFragment(),CategoryAdapter.OnItemCallback {
    companion object {
        fun newInstance(): FragmentCategory = FragmentCategory()
    }

    @Inject
    lateinit var viewModel: MainViewModel
    lateinit var adapter: CategoryAdapter
    lateinit var listener:NavigationInterface
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        viewModel.getAllCategory()
        viewModel.categoryData.observe(this, Observer {
            if (it != null) {
                adapter = CategoryAdapter(it,this)
                fragment_category_recycler_view.layoutManager=LinearLayoutManager(context)
                fragment_category_recycler_view.adapter=adapter
            }
        })
    }

    override fun onCategoryItemClicked(categoryId: String) {
        listener.onCategorySelected(categoryId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener=context as NavigationInterface
    }

    interface NavigationInterface{
        fun onCategorySelected(categoryId: String)
    }
}