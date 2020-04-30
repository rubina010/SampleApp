package com.example.practice

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practice.main.DiscoverPostAdapter
import com.example.practice.main.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_contents.*
import javax.inject.Inject

const val CATEGORY_ID = "category_id"

class FragmentContents : DaggerFragment(), DiscoverPostAdapter.OnItemCallbacks {
    companion object {
        fun newInstance(categoryId: String): FragmentContents = FragmentContents().apply {
            arguments = Bundle().apply {
                this.putString(CATEGORY_ID, categoryId)
            }
        }
    }

    @Inject
    lateinit var viewModel: MainViewModel
    lateinit var adapter: DiscoverPostAdapter
    var categoryId: String? = null
    lateinit var listener:NavigationInterface
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        fragment_content_back_text_view.setOnClickListener {
            activity?.onBackPressed()
        }
        arguments?.let {
            categoryId = it.getString(CATEGORY_ID)
        }
        adapter = DiscoverPostAdapter(context!!, this)
        viewModel.getDataSource(categoryId!!)
        viewModel.itemPagedList?.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            fragment_content_recycler_view.layoutManager = GridLayoutManager(context!!, 2)
            fragment_content_recycler_view.adapter = adapter
        })
    }

    override fun onPostClicked(deepLinkUrl:String) {
        listener.onPostItemClicked(deepLinkUrl)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener=context as NavigationInterface
    }

    interface NavigationInterface{
        fun onPostItemClicked(deepLinkUrl: String)
    }
}