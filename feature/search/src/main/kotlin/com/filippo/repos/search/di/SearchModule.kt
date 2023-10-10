package com.filippo.repos.search.di

import com.filippo.repos.search.data.RecentSearchesDataSourceImpl
import com.filippo.repos.search.domain.RecentSearchesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SearchModule {

    @Binds
    fun bindsRecentSearchesDataSource(impl: RecentSearchesDataSourceImpl): RecentSearchesDataSource
}
