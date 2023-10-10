package com.filippo.repos.details.di

import com.filippo.repos.details.data.CommitsDataSourceImpl
import com.filippo.repos.details.data.RepositoryDataSourceImpl
import com.filippo.repos.details.domain.CommitsDataSource
import com.filippo.repos.details.domain.RepositoryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DetailsDomainModule {

    @Binds
    fun bindsCommitsDataSource(impl: CommitsDataSourceImpl): CommitsDataSource

    @Binds
    fun bindsRepositoryDataSource(impl: RepositoryDataSourceImpl): RepositoryDataSource
}
