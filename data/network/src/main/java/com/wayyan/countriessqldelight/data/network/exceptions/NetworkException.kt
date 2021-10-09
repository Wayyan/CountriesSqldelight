package com.wayyan.countriessqldelight.data.network.exceptions

import java.io.IOException

data class NetworkException constructor(
    val errorBody: String? = null,
    var errorCode: Int = 0
) : IOException()