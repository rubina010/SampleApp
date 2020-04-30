package com.example.practice.di.module

import com.example.practice.utils.BaseSchedulerProvider
import com.example.practice.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class RxModule {

    @Provides
    fun providesCompositeDisposable() = CompositeDisposable()

    @Provides
    fun providesSchedulerProvider() = SchedulerProvider()

    @Provides
    fun providesBaseSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider()
}