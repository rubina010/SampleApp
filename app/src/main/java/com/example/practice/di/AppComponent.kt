package com.example.practice.di

import android.app.Application
import com.example.practice.Practice
import com.example.practice.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidSupportInjectionModule::class), (ActivityBindingModule::class), (FragmentBindingModule::class), (ApplicationBindingModule::class),
        (ApiModule::class),(RxModule::class),(AppModule::class)]
)
interface AppComponent : AndroidInjector<Practice> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}
