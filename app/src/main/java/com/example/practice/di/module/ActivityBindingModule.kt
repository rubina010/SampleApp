package com.example.practice.di.module

import com.example.practice.main.MainActivity
import com.example.practice.di.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}