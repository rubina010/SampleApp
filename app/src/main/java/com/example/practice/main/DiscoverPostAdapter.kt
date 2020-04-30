package com.example.practice.main

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.practice.R
import com.example.practice.entity.DiscoverPostList


val DIFF_UTILS = object : DiffUtil.ItemCallback<DiscoverPostList>() {
    override fun areContentsTheSame(oldItem: DiscoverPostList, newItem: DiscoverPostList): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: DiscoverPostList, newItem: DiscoverPostList): Boolean {
        return oldItem.title == newItem.title
    }

}

class DiscoverPostAdapter(
    val context: Context,
    val listener: DiscoverPostAdapter.OnItemCallbacks
) : PagedListAdapter<DiscoverPostList, DiscoverPostAdapter.DiscoverPostViewHolder>(DIFF_UTILS) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverPostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return DiscoverPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscoverPostViewHolder, position: Int) {
        val model = getItem(position)
        holder.postTitleTextView.text = model!!.title
        Glide.with(context).setDefaultRequestOptions(RequestOptions()).load(model.media)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }
            }).into(holder.imageview)
        /* val uri= Uri.encode(model.deep_link_url)
        holder.postTitleTextView.text =
            Html.fromHtml("<a href=\"${uri}\">${model.title}</a>")
        holder.postTitleTextView.movementMethod = LinkMovementMethod.getInstance()*/
        holder.rootLayout.setOnClickListener {
            listener.onPostClicked(model.deep_link_url)
        }
    }

    class DiscoverPostViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var postTitleTextView =
            view.findViewById<TextView>(R.id.item_post_title_text_view)!!
        var progressBar = view.findViewById<ProgressBar>(R.id.item_post_progress_bar)!!
        var rootLayout: LinearLayout = view.findViewById(R.id.item_post_root_linear_layout)
        var imageview: ImageView = view.findViewById(R.id.item_post_thumbnail_image_view)
    }

    interface OnItemCallbacks {
        fun onPostClicked(deepLinkUrl:String)
    }
}