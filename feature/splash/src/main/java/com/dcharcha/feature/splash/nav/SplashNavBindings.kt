package com.dcharcha.feature.splash.nav

import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.core.navigation.StartDestinationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SplashNavBindings {
    @Provides
    @IntoSet
    @Singleton
    fun graph(): FeatureNavGraph = SplashGraph

    @Provides
    @IntoSet
    @Singleton
    fun start(): StartDestinationProvider = SplashGraph
}
