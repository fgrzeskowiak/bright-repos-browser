package com.filippo.repos.details.di

import com.filippo.repos.details.data.remote.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object DetailsApiModule {

    @Provides
    fun providesGithubApi(retrofit: Retrofit): GithubApi = retrofit.create()
}
