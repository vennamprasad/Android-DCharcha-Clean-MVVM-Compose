package com.dcharcha.feature.auth

import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.feature.auth.nav.AuthGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthNavBindings {
    @Provides
    @IntoSet
    @Singleton
    fun graph(): FeatureNavGraph = AuthGraph()
}