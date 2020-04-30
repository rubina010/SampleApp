package com.example.practice.di.module

import com.example.practice.FragmentCategory
import com.example.practice.FragmentContents
import com.example.practice.di.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindCategoryFragment():FragmentCategory

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindContentFragment(): FragmentContents

}