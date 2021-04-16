package com.example.theustimes.di.modules

import com.example.theustimes.network.NetworkConfiguration
import com.example.theustimes.network.NetworkConfigurationImpl
import com.example.theustimes.network.NetworkProvider
import com.example.theustimes.network.NetworkProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NetworkModule {

    @Binds
    @Singleton
    fun bindNetworkProvider(networkProvider: NetworkProviderImpl): NetworkProvider

    @Binds
    @Singleton
    fun bindNetworkConfiguration(networkConfiguration: NetworkConfigurationImpl): NetworkConfiguration
}