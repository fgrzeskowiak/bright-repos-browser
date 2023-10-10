package com.filippo.repos.navigation.di

import com.filippo.repos.navigation.data.NavigatorImpl
import com.filippo.repos.navigation.domain.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

    @Binds
    @Singleton
    fun bindsNavigator(impl: NavigatorImpl): Navigator
}
