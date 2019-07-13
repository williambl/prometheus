package com.williambl.prometheus.common.fourthwall

object FourthWallHelper {

    fun getCurrentUser(): String {
        return System.getProperty("user.name")
    }

}

