package com.example.practice.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.practice.ItemPostDataSourceFactory
import com.example.practice.entity.CategoryModel
import com.example.practice.entity.DiscoverPostList
import com.example.practice.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val app: Application,
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    val repository: MainRepository
) : AndroidViewModel(app) {
    val categoryData = MutableLiveData<List<CategoryModel>>()
    var itemPagedList: LiveData<PagedList<DiscoverPostList>>? = MutableLiveData()

    fun getAllCategory() {
        compositeDisposable.add(repository.getAllCategory()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribeBy(
                onNext = {
                    Log.i("skdjfhsk", "$it")
                    categoryData.postValue(it)
                },
                onError = {
                    Log.i("skdjfhsk", "$it")
                    it.printStackTrace()
                }
            ))
    }

    fun getDataSource(categoryId: String) {
        val itemForYouDataSourceFactory = ItemPostDataSourceFactory(
            repository.apiService,
            categoryId
        )
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()
        itemPagedList = LivePagedListBuilder(itemForYouDataSourceFactory, config).build()
    }
}