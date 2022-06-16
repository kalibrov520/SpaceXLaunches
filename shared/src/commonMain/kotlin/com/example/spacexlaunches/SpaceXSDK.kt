package com.example.spacexlaunches

import com.example.spacexlaunches.cache.Database
import com.example.spacexlaunches.cache.DatabaseDriverFactory
import com.example.spacexlaunches.entity.RocketLaunch
import com.example.spacexlaunches.network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    @Throws(Exception::class) suspend fun getLaunches(forceLoad: Boolean): List<RocketLaunch> {
        val cachedRocketLaunches = database.getAllLaunches()
        return if (cachedRocketLaunches.isNotEmpty() && !forceLoad) {
            cachedRocketLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}