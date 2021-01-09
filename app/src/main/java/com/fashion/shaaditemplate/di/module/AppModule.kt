package com.fashion.shaaditemplate.di.module

import com.fashion.shaaditemplate.data.network.ApiHelper
import com.fashion.shaaditemplate.data.network.AppApiHelper
import com.fashion.shaaditemplate.data.persistence.db.AppDbHelper
import com.fashion.shaaditemplate.data.persistence.db.DbHelper
import com.fashion.shaaditemplate.di.annotation.CoroutineScopeIO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @CoroutineScopeIO
    internal fun provideCoroutineScopeIO(): CoroutineScope = CoroutineScope(Dispatchers.IO)

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper = appDbHelper

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

}