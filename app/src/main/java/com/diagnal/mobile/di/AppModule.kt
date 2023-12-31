package com.diagnal.mobile.di

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.diagnal.mobile.local.LocalVideoDetailSource
import com.diagnal.mobile.models.VideoDetail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePager(localSource: LocalVideoDetailSource): Pager<Int, VideoDetail> {
        return Pager(config = PagingConfig(20), pagingSourceFactory = { localSource })
    }
}