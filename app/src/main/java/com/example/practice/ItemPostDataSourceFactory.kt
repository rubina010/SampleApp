package com.example.practice

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.practice.entity.DiscoverPostList
import com.example.practice.networking.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ItemPostDataSourceFactory(
    val apiService: ApiService,
    val categoryId: String?
) : DataSource.Factory<Int, DiscoverPostList>() {
    override fun create(): DataSource<Int, DiscoverPostList> {
        return ItemCategoryDataSource(
            apiService,
            categoryId
            )
    }
}

const val PER_PAGE = 10
const val FIRSTPAGE: Int = 1

class ItemCategoryDataSource(
    val apiService: ApiService,
    val categoryId: String?
) : PageKeyedDataSource<Int, DiscoverPostList>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DiscoverPostList>
    ) {
        apiService.getPostByCategory(FIRSTPAGE, categoryId!!, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    if (it.data.posts != null) {
                        callback.onResult(it.data.posts, null, FIRSTPAGE + 1)
                    }
                    Log.i("skdhfskjd","success $it")
                },
                onError = {
                    Log.i("skdhfskjd","failure $it")
                    it.printStackTrace()
                }
            )
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, DiscoverPostList>
    ) {
        apiService.getPostByCategory(params.key, categoryId!!, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    val keyToUse = if (params.key > 1)
                        params.key - 1
                    else
                        null
                    if (it.data.posts != null) {
                        callback.onResult(it.data.posts, keyToUse)
                    }

                },
                onError = {
                    it.printStackTrace()
                }
            )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DiscoverPostList>) {
        apiService.getPostByCategory(params.key, categoryId!!, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    val keyToUse = if (it.data.count % 10 != 0.toLong())
                        params.key + 1
                    else
                        null
                    if (it.data.posts != null) {
                        callback.onResult(it.data.posts, keyToUse)
                    }

                },
                onError = {
                    it.printStackTrace()
                }
            )
    }

}